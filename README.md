# AlarmMyHome
iseng eksperiment membuat alarm di raspberrypi set alarmnya lewat smartphone yang beros android

## Pada Raspberry pi
alarm.py secara default akan mengakses atau membuat jika tidak ada /home/pi/jam.txt jadi usahakan menggunakan user pi atau kamu ubah pathnya sesuai kebutuhanmu

usahakan apiwaker.py dan alarm.py dalam satu folder silahkan ubah route sesuai keinginanmu, saya menggunakan flask jadi jangan lupa install flask menggunakan pip.

alarm.py aku buat menggunakan python2.7 jadi kalo ingin mengggunakan python3 silakan sesuaikan sendiri

## Pada Android

Ubah IP address pada ApiDataSetInterface.kt dengan ip address raspberrymu dan port yang kamu set pada apiwaker.py
```
   val BASE_URL="http://<your ip raspberry pi>:<your port in apiwaker.py>/"
``` 
