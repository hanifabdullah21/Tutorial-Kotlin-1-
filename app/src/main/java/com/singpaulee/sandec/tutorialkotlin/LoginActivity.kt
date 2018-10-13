package com.singpaulee.sandec.tutorialkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var sp = SharedPrefManager(this@LoginActivity)          //panggil kelas SharedPrefManager

        //TODO CEK SUDAH LOGIN ATAU BELUM
        if (sp.getIsLogin()){
            finish()
            startActivity(intentFor<MainActivity>())
        }

        //TODO HANDLE KETIKA MENEKAN TOMBOL MASUK
        btn_masuk.onClick {
            if (!validasi()){
                return@onClick
            }

            sp.savePrefString(sp.USERNAME, edt_username.text.toString())    //simpan username
            sp.savePrefString(sp.PASSWORD, edt_password.text.toString())    //simpan password
            sp.savePrefBoolean(sp.isLOGIN, true)                            //simpan pernyataan bahwa sudah login

            finish()
            startActivity(intentFor<MainActivity>())    //Anko commons
        }
    }

    /* Fungsi untuk mengecek apakah ada form yang belum diisi
    *
    * */
    private fun validasi(): Boolean {
        //Cek username kosong atau tidak
        if (edt_username.text.toString().isBlank()){
            edt_username.error = "Tidak Boleh Kosong"
            edt_username.requestFocus()
            return false
        }

        //Cek password kosong atau tidak
        if (edt_password.text.toString().isBlank()){
            edt_password.error = "Tidak Boleh Kosong"
            edt_password.requestFocus()
            return false
        }

        return true
    }
}
