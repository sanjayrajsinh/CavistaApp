package com.cavista.cavistaapp.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


object DialogUtils {

        fun toast(context: Context?, message: String) {
                if (context != null && !TextUtils.isEmpty(message)) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
        }

        /**
         * Show Default dialog.
         *
         * @param context Application/Activity Context for creating dialog.
         * @param title   Title of dialog
         * @param message Message of dialog
         */
        fun dialog(context: Context, title: String, message: String) {

                val builder = AlertDialog.Builder(context)
                builder.setTitle(title)
                builder.setMessage(message)
                builder.setCancelable(false)
                builder.setPositiveButton(context.getString(android.R.string.ok)) { dialog, which -> dialog.dismiss() }
                val dialog = builder.create()
                if (!dialog.isShowing)
                        dialog.show()
        }

        /**
         * Show Default dialog with ok and cancel two buttons.
         *
         * @param context Application/Activity Context for creating dialog.
         * @param title   Title of dialog
         * @param message Message of dialog
         * @param callback Callback for button click of dialog
         */
        fun okCancelDialog(context: Context, title: String, message: String, callback: OkCancelDialogInterface) {

                val builder = AlertDialog.Builder(context)
                builder.setTitle(title)
                builder.setMessage(message)
                builder.setCancelable(false)
                builder.setPositiveButton(context.getString(android.R.string.ok)) { dialog, which ->
                        dialog.dismiss()
                        callback.ok()
                }
                builder.setNegativeButton(context.getString(android.R.string.cancel)) { dialog, which ->
                        dialog.dismiss()
                        callback.cancel()
                }
                val dialog = builder.create()
                if (!dialog.isShowing)
                        dialog.show()
        }




        fun showSnackBar(context: Context?, message: String) {
                /*   if (context != null && !TextUtils.isEmpty(message)) {
                       val snackbar = Snackbar.make((context as Activity).findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                       val view = snackbar.view
                       val params = view.layoutParams as FrameLayout.LayoutParams
                       val tv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
                       val font = Typeface.createFromAsset(context.assets, FontUtils.AVENIR_REGULAR)
                       tv.typeface = font
                       tv.textSize = 16f
                       tv.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
                       view.layoutParams = params
                       snackbar.show()
                   }*/
        }


}
