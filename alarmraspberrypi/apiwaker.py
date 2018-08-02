from flask import Flask,request,abort,jsonify
from alarm import Bunyi

app = Flask(__name__)

@app.route('/setalarm/api/v1.0', methods=['POST'])
def set_alarm():
   if not request.json or not 'time' in request.json:
      return jsonify({"status":"failure to set check your json"}),200
   jam=request.json.get('time',"")
   bunyi = Bunyi(jam)
   return jsonify({'status':'set alarm done to {}.'.format(bunyi.read_file())}),201

if __name__ == '__main__':
   app.run(host='0.0.0.0',debug=True,port=200)
