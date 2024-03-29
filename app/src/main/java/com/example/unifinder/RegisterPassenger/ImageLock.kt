package com.example.unifinder.RegisterPassenger

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageLock : AppCompatActivity(), View.OnClickListener {
    var dividedImages = mutableListOf<Bitmap>()
    val dividedImages2 = mutableListOf<Bitmap>()
    val shuffled = mutableListOf<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imglock)

        val originalImage = BitmapFactory.decodeResource(
            resources, R.drawable.arrow_back
        ) // Replace with your image resource

//        showImage(originalImage)
        findViewById<Button>(R.id.btn1).setOnClickListener {
            dividedImages.clear()
            shuffled.clear()
            dividedImages2.clear()
            selectFun()
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            shuffledImages()
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

    private fun showImage(image: Bitmap) {
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageBitmap(image)


        dividedImages.addAll(
            divideImageIntoParts(image)
        )

        shuffledImages()

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
            imageViews[i].background = ContextCompat.getDrawable(this@ImageLock, android.R.color.transparent)
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
                v?.background = ContextCompat.getDrawable(this@ImageLock, android.R.color.transparent)

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

    private val changeImage =
        registerForActivityResult(
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