#!/usr/bin/env python
from datetime import datetime
import subprocess as term
import os
class Bunyi:
   def __init__(self,muni):
       self.muni=muni
       if not os.path.isfile('/home/pi/jam.txt'):
          self.create(self.muni)
       else:
          self.rewrite(self.muni)

   def rewrite(self,text):
      f = open('/home/pi/jam.txt', 'r+')
      f.seek(0)
      f.truncate()
      f.write(str(text))
      f.close()

   def create(self,text):
      f = open('/home/pi/jam.txt', 'a')
      f.write(str(text))
      f.close()

   def read_file(self):
      f = open('/home/pi/jam.txt', 'r+')
      self.muni=f.read()
      f.close()
      return self.muni

def weker():
   while True:
      saiki =str (datetime.now().time())
      spl = saiki.split('.')
      if spl[0] == bunyi.read_file():
         p = term.Popen(["/home/pi/ProjectAlarm/alarmraspberrypi/buzzertone"], stdout=term.PIPE)
         msg,err = p.communicate()
         print(msg)



if __name__ == '__main__':
   muni = "00:00:00"
   bunyi = Bunyi(muni)
   if bunyi is not None:
      weker()
