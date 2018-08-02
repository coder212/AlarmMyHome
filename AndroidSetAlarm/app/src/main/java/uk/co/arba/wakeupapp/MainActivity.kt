package uk.co.arba.wakeupapp

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var jam:String=""
    var menit:String=""
    var detik:String=""
    val NAME_PREFS = "WekerPrefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        tpicker.setIs24HourView(true)
        Log.d("test","out")
        detik = "00"
        prefs = this.getSharedPreferences(NAME_PREFS, 0)
        val timesave = prefs!!.getString("time","00:00:00")
        textAlarmset.setText("alarm berbunyi pada jam "+timesave)

        Log.d("test","out1")
        tpicker.setOnTimeChangedListener(TimePicker.OnTimeChangedListener {tpicker, jam, menit ->
            this.jam = String.format("%02d",jam)
            this.menit= String.format("%02d", menit)

        })
        Log.d("test","out2")
        setButton.setOnClickListener { view: View ->
            Log.d("test","string")
            val apiSetDataInterface = ApiSetDataInterface.create()
            val sendModel = SendModel()
            if (this.jam.equels(""){
                this.jam = "00"
            }
            if (this.menit.equels("")){
                this.menit = "00"
            }
            sendModel.time = this.jam+":"+this.menit+":"+this.detik
            Log.d("test",sendModel.time)
            val panggil = apiSetDataInterface.sending(sendModel)
            panggil.enqueue(object : Callback<ReceiveModel>{
                override fun onFailure(call: Call<ReceiveModel>?, t: Throwable?) {
                   // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<ReceiveModel>?, response: Response<ReceiveModel>?) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    if(response != null){
                        var berhasil : String = response.body().success
                        prefs = this@MainActivity.getSharedPreferences(NAME_PREFS,0)
                        val save = prefs!!.edit()
                        save.putString("time",sendModel.time)
                        save.apply()
                        textAlarmset.setText("alarm berbunyi pada jam "+sendModel.time)
                        Toast.makeText(this@MainActivity, berhasil, Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
