package uk.co.arba.wakeupapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class SendModel{
    @SerializedName("time")
    var time :String="00:00:00"
}