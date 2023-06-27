package com.example.unifinder.RegisterPassenger


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.unifinder.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ImageLocktwo : AppCompatActivity(), View.OnClickListener {
    var dividedImages = mutableListOf<Bitmap>()
    val dividedImages2 = mutableListOf<Bitmap>()
    val shuffled = mutableListOf<Bitmap>()
    var userData: User? = null
    var uid: String? = null

    fun addImageOnline(userRef: DatabaseReference) {
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Access the user data from the snapshot
                    userData = dataSnapshot.getValue(User::class.java)
                    // Handle the retrieved user data
                    if (userData != null) {
                        val userEmail = userData?.email
                        val name = userData?.name
                        val img = userData?.img
                        Log.d("123123", "onDataChange: $userEmail")
                        Log.d("123123", "onDataChange: $name")
                        Log.d("123123", "onDataChange: $img ")
                        // Access other user data fields as needed
                        selectedFun(userData!!.img)
                    }
                } else {
                    // User data does not exist
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error case
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.unifinder.R.layout.activity_imglock)


        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")
        uid = intent.getStringExtra("uid")
        val userRef = usersRef.child(uid!!)
        addImageOnline(userRef)

//        showImage(originalImage)
        findViewById<Button>(R.id.btn1).visibility = View.GONE/*   findViewById<Button>(R.id.btn1).setOnClickListener {
                   dividedImages.clear()
                   shuffled.clear()
                   dividedImages2.clear()
                   selectFun()
               }*/
        findViewById<Button>(R.id.btn2).setOnClickListener {
//            shuffledImages()
            dividedImages.clear()
            shuffled.clear()
            dividedImages2.clear()

        }


    }

    fun shuffledImages() {
        shuffled.clear()
        dividedImages2.clear()
        shuffled.addAll(
            dividedImages.shuffled()
        )
        showDividedImages(shuffled)
    }

    private fun showImage(image: String) {
        val imageView: ImageView = findViewById(R.id.imageView)
//        imageView.setImageBitmap(image)

        Picasso.with(this).load(image).into(imageView)
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            val bm: Bitmap? = (imageView.drawable as BitmapDrawable?)?.bitmap
            dividedImages.addAll(
                divideImageIntoParts(bm!!)
            )

            shuffledImages()
        }


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
                ContextCompat.getDrawable(this@ImageLocktwo, android.R.color.transparent)
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
//        Toast.makeText(this, "$index", Toast.LENGTH_SHORT).show()
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
                    ContextCompat.getDrawable(this@ImageLocktwo, android.R.color.transparent)

            }
        }
        if (dividedImages2.size == shuffled.size) {
//            Toast.makeText(this, "GameWon", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, HomeScreen::class.java).apply {
                putExtra("uid", uid)
            })
        }

        Log.d("TAG", "onClick:123123 ${dividedImages2.size}")
    }

    fun selectFun() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }

    fun selectedFun(uri: String) {
        Log.d("123123", "selectedFun: $uri")
//        val imageView: ImageView = findViewById(R.id.imageView)
//        imageView.setImageBitmap(image)

//        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.parse(uri))
        showImage(uri)
    }

    private val changeImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data
            val imgUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imgUri)

            //            showImage(bitmap)
        }
    }

}