# AlarmMyHome
iseng eksperiment membuat alarm di raspberrypi set alarmnya lewat smartphone yang beros android

## Pada Raspberry pi
alarm.py secara default akan mengakses atau membuat jika tidak ada /home/pi/jam.txt jadi usahakan menggunakan user pi atau kamu ubah pathnya sesuai kebutuhanmu

usahakan apiwaker.py dan alarm.py dalam satu folder silahkan ubah route sesuai keinginanmu, saya menggunakan flask jadi jangan lupa install flask menggunakan pip.

alarm.py aku buat menggunakan python2.7 jadi kalo ingin mengggunakan python3 silakan sesuaikan sendiri
app route di apiwaker.py bisa diganti bebas misalnya
```
    @app.route('/setalarm/api/v1.0', methods=['POST']
````
dapat diganti menjadi
```
    @app.route('/settingalarm', methods=['POST'])
```
buzzertone.s merupakan code untuk membunyikan buzzer saya buat menggunakan assembly dan untuk access gpionya menggunakan wiringpi bisa install wiringpi atau silakan gunakan python gpio jika tidak berkenan menginstall wiringpi
compilenya menggunakan perintah
```
   gcc -o buzzertone buzzertone.s -lwiringpi
```
ubah juga alarm.py yang ada dibagian
```
    p = term.Popen(["/home/pi/ProjectAlarm/alarmraspberrypi/buzzertone"], stdout=term.PIPE)
```
menyesuaikan degan lokasi dari buzzertone yang ada di raspimu

## Pada Android

Ubah IP address pada ApiDataSetInterface.kt dengan ip address raspberrymu dan port yang kamu set pada apiwaker.py
```
   val BASE_URL="http://<your ip raspberry pi>:<your port in apiwaker.py>/"
``` 
sesuaikan juga ApiDataSetInterfacenya
```
    @POST("setalarm/api/v1.0")
````
bisa diubah menyesuaikan dengan yang ada di apiwaker.py menjadi
```
    @POST("settingalarm")
```
