from flask import Flask, jsonify, request
from flask_cors import CORS # 보안문제 해결하기 위해서 넣은 것 // 스프링부트에서 크로스 오리진과 똑같은 방식임
from script import *

# instantiate the app
app = Flask(__name__)

# enable CORS
CORS(app, resources={r'/*': {'origins': '*'}}) # 크로스 로이진

@app.route('/predict', methods=['POST']) # 이 방식으로 통신이 들어오면, 즉 스프링에서 Postmapping과 비슷 # predict 라는 이름으로 들어오면 받는 것
def predict():
    f = request.form['path'] # Form 데이터의 path 값을 f 에 넣어주고,
    label = predict_return(f.replace("\\","/").replace(" ","")) # 경로인식을 바꿔줌 # predict_return은 script.py 를 가져온 것 
    # label: Happy, probablity: 0.222~ 
    print(label) 
    return jsonify(label) # JSON 형식으로 바꿔준다. (Axios 는 JSON형식으로 통신한다.) -> 이렇게 해서 응답해주고

if __name__ == '__main__':
    app.run(debug=True)