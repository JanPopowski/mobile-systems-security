package com.example.secretlab.data

import android.content.Context

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

class LocalAccountVault(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedBox = EncryptedSharedPreferences.create(
        context,
        BIN_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveLocalAccount(mail: String, secret: String) {
        // here write solution to C05.5
        encryptedBox.edit {
            putString(MAIL_SLOT, mail)
                .putString(SECRET_SLOT, secret)
        }
    }

    fun readAccountMail(): String? {
        // here write solution to C05.5
        return encryptedBox.getString(MAIL_SLOT, null)
    }

    fun readAccountSecret(): String? {
        // here write solution to C05.5
        return encryptedBox.getString(SECRET_SLOT, null)
    }

    companion object {
        const val BIN_NAME = "account_memory"
        const val MAIL_SLOT = "owner_mail"
        const val SECRET_SLOT = "owner_secret"
    }
}
