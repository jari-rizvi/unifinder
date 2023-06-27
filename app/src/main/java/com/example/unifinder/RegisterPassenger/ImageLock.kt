package com.example.unifinder.RegisterPassenger

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.unifinder.HashObject.hashFunction
import com.example.unifinder.HashObject.progressDialog
import com.example.unifinder.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class ImageLock : AppCompatActivity(), View.OnClickListener {
    var dividedImages = mutableListOf<Bitmap>()
    val dividedImages2 = mutableListOf<Bitmap>()
    val shuffled = mutableListOf<Bitmap>()
    var uid: String = ""
    var email: String = ""
    var displayName: String = ""
    var img: String = ""
    var userRef: DatabaseReference? = null

    fun addImageOnline(userRef: DatabaseReference, urlImg: String) {

        userRef.child("email").setValue(email)
        userRef.child("name").setValue(displayName)
        userRef.child("img").setValue("$urlImg")
        userRef.child("hashId").setValue("${hashFunction(email)}")
        Log.d("123123", "Validate:$email ")
        Log.d("123123", "Validate:$displayName ")
        Log.d("123123", "Validate:$uid ")
        Log.d("123123", "Validate:$userRef ")
        userRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val builder = AlertDialog.Builder(this@ImageLock)
                val inflater: LayoutInflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.hash_copy, null)
                builder.setView(dialogView)

                // Find views within the custom layout
                val messageTextView = dialogView.findViewById<TextView>(R.id.tv_title_text)
                val okButton = dialogView.findViewById<Button>(R.id.okBtn)
                val dialog = builder.create()

                messageTextView.text = "${hashFunction(email)}"
                okButton.setOnClickListener { v: View? ->
                    val clipboard: ClipboardManager? =
                        this@ImageLock.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                    val clip = ClipData.newPlainText("io", "${messageTextView.text.toString()}")
                    clipboard!!.setPrimaryClip(clip)
                    dialog.dismiss()
                }
                dialog.setCancelable(false)
                dialog.show()

                dialog.setOnDismissListener {
                    startActivity(Intent(this@ImageLock, GetPdf::class.java).apply {
                        putExtra("email", email)
                    })
                }


            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imglock)


        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

        userRef = usersRef.child(intent.getStringExtra("uid")!!)

        uid = intent.getStringExtra("uid")!!
        email = intent.getStringExtra("email")!!
        displayName = intent.getStringExtra("displayName")!!
        img = intent.getStringExtra("img")!!
//        showImage(originalImage)
        findViewById<Button>(R.id.btn1).setOnClickListener {
            dividedImages.clear()
            shuffled.clear()
            dividedImages2.clear()
            selectFun()
        }
//        findViewById<Button>(R.id.btn2).setOnClickListener {
//            shuffledImages()
//        }


    }


    private fun showImage(image: Bitmap) {

        progressDialog(this@ImageLock, layoutInflater).show()
        val storage = FirebaseStorage.getInstance()


        val storageRef = storage.reference

        val imageRef = storageRef.child("images/$uid.jpg")
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()


        val uploadTask = imageRef.putBytes(imageBytes)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            // Image uploaded successfully
            // Retrieve the download URL if needed
            if (taskSnapshot.task.isComplete) {
                val downloadUrl = taskSnapshot.metadata?.reference?.downloadUrl
                downloadUrl!!.addOnCompleteListener {
                    progressDialog(this@ImageLock, layoutInflater).dismiss()
                    addImageOnline(userRef!!, it.result!!.toString())
                }

            }
        }.addOnFailureListener { exception ->
            // Image upload failed
            // Handle the failure and display an error message
        }


        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageBitmap(image)


//        dividedImages.addAll(
//            divideImageIntoParts(image)
//        )
//
//        shuffledImages()

    }

    private fun showImage2(image: Uri?) {
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageURI(image)
    }

    private fun showDividedImages(images: List<Bitmap>) {
        val imageViews = arrayOf(
            findViewById<ImageView>(R.id.imageView1),
            findViewById<ImageView>(R.id.imageView2),
            findViewById<ImageView>(R.id.imageView3),
            findViewById<ImageView>(R.id.imageView4),
            findViewById<ImageView>(R.id.imageView5),
            findViewById<ImageView>(R.id.imageView6),
            findViewById<ImageView>(R.id.imageView7),
            findViewById<ImageView>(R.id.imageView8),
            findViewById<ImageView>(R.id.imageView9)
        )

        for (i in 0 until 9) {
            imageViews[i].setImageBitmap(images[i])
            imageViews[i].setOnClickListener(this)
            imageViews[i].background =
                ContextCompat.getDrawable(this@ImageLock, android.R.color.transparent)
        }
    }

    fun divideImageIntoParts(originalImage: Bitmap): List<Bitmap> {
        val parts = mutableListOf<Bitmap>()
        val imageWidth = originalImage.width
        val imageHeight = originalImage.height
        val partWidth = imageWidth / 3
        val partHeight = imageHeight / 3

        for (row in 0 until 3) {
            for (col in 0 until 3) {
                val startX = col * partWidth
                val startY = row * partHeight
                val part = Bitmap.createBitmap(
                    originalImage, startX, startY, partWidth, partHeight
                )
                parts.add(part)
            }
        }

        return parts
    }

    var counter = 0
    override fun onClick(v: View?) {
        var index = 0;
        when (v?.id) {

            R.id.imageView1 -> {
                index = 1 - 1

            }

            R.id.imageView2 -> {
                index = 2 - 1

            }

            R.id.imageView3 -> {
                index = 3 - 1

            }

            R.id.imageView4 -> {
                index = 4 - 1

            }

            R.id.imageView5 -> {
                index = 5 - 1

            }

            R.id.imageView6 -> {
                index = 6 - 1

            }

            R.id.imageView7 -> {
                index = 7 - 1

            }

            R.id.imageView8 -> {
                index = 8 - 1

            }

            R.id.imageView9 -> {
                index = 9 - 1

            }
        }
        Toast.makeText(this, "$index", Toast.LENGTH_SHORT).show()
        if (shuffled.get(index).equals(dividedImages.get(counter))) {


            if (dividedImages2.contains(shuffled.get(index))) {
                Toast.makeText(this, "Already$index", Toast.LENGTH_SHORT).show()
            } else {
                v?.background = ContextCompat.getDrawable(this, R.drawable.arrow_back)
                counter++
                dividedImages2.add(shuffled.get(index))
            }
        } else {
            v?.background = ContextCompat.getDrawable(this, R.drawable.arrow_back)
            GlobalScope.launch(Dispatchers.Main) {
                delay(500)
                v?.background =
                    ContextCompat.getDrawable(this@ImageLock, android.R.color.transparent)

            }
        }
        if (dividedImages2.size == shuffled.size) {
            Toast.makeText(this, "GameWon", Toast.LENGTH_SHORT).show()
        }

        Log.d("TAG", "onClick:123123 ${dividedImages2.size}")
    }

    fun selectFun() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }

    private val changeImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data
            val imgUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imgUri)
            showImage(bitmap)
        }
    }


}


