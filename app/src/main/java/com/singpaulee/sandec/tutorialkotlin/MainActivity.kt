package com.singpaulee.sandec.tutorialkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    companion object {
        val tvUserID = 199
    }

    lateinit var sp : SharedPrefManager //Variabel / objek untuk kelas SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        MainActivityUI().setContentView(this)

        sp = SharedPrefManager(this)               //panggil kelas SharedPrefManager

        var username = getUsername()               //mendapatkan username dari sharedpreference kemudian ditampung dalam variabel 'username'

        val tvUser = find<TextView>(tvUserID)    //Buat variabel textview untuk menyambungkan dengan layout
        tvUser.text = "Halo $username"                    //Atur teks pada textview yang telah dibuat
    }

    //TODO BUAT FUNGSI MENDAPATKAN DATA DARI SHAREDPREF
    private fun getUsername() : String {
        return sp.getUsername()
    }

    //TODO BUAT LAYOUT MENGGUNAKAN ANKO LAYOUT
    class MainActivityUI: AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>)= with(ui) {
            verticalLayout {
                imageView(R.drawable.dsc_logo){
                    scaleType = ImageView.ScaleType.CENTER_INSIDE
                }.lparams(dip(300), dip(300)){
                    gravity = Gravity.CENTER
                    topMargin = dip(10)
                }

                textView {
                    id = tvUserID
                    textSize = sp(18).toFloat()
                    gravity = Gravity.CENTER
                    text = "Halooo"
                }.lparams(matchParent, wrapContent){
                    margin = dip(10)
                }
            }
        }
    }

    //TODO MENGATUR TOMBOL KEMBALI
    override fun onBackPressed() {
//        super.onBackPressed()
        alert ("Apakah anda ingin logout ?"){
            yesButton {
                toast("Kamu sudah logout")
                sp.savePrefBoolean(sp.isLOGIN,false)
                startActivity(intentFor<LoginActivity>())
                finish()
            }
            noButton {
                toast("Keluar")
                super.onBackPressed()
            }
        }.show()
    }
}
