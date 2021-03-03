package com.example.examen

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.IBinder
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyService : Service() {
    private var num1: Int = 0
    private var num2: Int = 0
    private var op: String = ""
    private var result: Int = 0

    val channelId = "CanalNotificationExample"
    var notificationManager: NotificationManager? = null
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "Servicio arrancado", Toast.LENGTH_SHORT).show()

        if (intent != null && intent.hasExtra("nummero1")) {
            num1 = intent.getStringExtra("nummero1")!!.toInt()
            num2 = intent.getStringExtra("numero2")!!.toInt()
            op = intent.getStringExtra("operacion").toString()
        }

        if (op == "+"){
            result = num1 + num2
        }else if (op == "-"){
            result = num1 - num2
        }else if (op == "*"){
            result = num1 * num2
        }else if (op == "/"){
            result = num1 / num2
        }




        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var canal = NotificationChannel(channelId, "Nombre canal", NotificationManager.IMPORTANCE_DEFAULT)
        canal.description = "Descripcion"
        canal.enableVibration(true)
        canal.vibrationPattern = longArrayOf(100,100,500,200)

        notificationManager!!.createNotificationChannel(canal)
        sendNotification(num1, num2, op, result)

        return START_NOT_STICKY
    }

    fun sendNotification(num1: Int, num2: Int, op: String, result: Int) {
        val notificationIntent = Intent(applicationContext, MostrarResultado::class.java)
        notificationIntent.putExtra("num1", num1.toString())
        notificationIntent.putExtra("num2", num2.toString())
        notificationIntent.putExtra("op", op.toString())
        notificationIntent.putExtra("result", result.toString())
        val notificationId = 101

        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, MostrarResultado::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        val icon = Icon.createWithResource(this, android.R.drawable.ic_dialog_alert)

        val action = Notification.Action.Builder(icon, "ABRIR", pendingIntent).build()

        val notificacionBuilder = Notification.Builder(this, channelId)
            .setContentTitle("Resultado")
            .setContentText("El resultado de la operacion es: " + result.toString())
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setActions(action)

        notificationManager!!.notify(notificationId, notificacionBuilder.build())
    }
}