# importing needed modules-----------------------
import cv2, torch
# import numpy as np
from FERModel import *

# defining important funtions---------------------



# function to turn photos to tensor
def img2tensor(x):
    transform = transforms.Compose(
            [transforms.ToPILImage(),
            transforms.Grayscale(num_output_channels=1),
            transforms.ToTensor(),
            transforms.Normalize((0.5), (0.5))])
    return transform(x)

def predict(x):
    model = FERModel(1, 7)
    softmax = torch.nn.Softmax(dim=1)
    model.load_state_dict(torch.load('/Users/HyeonJae/nhj/final_project/final_web/Web/EmotionalClassificationWeb/back_flask/FER2013-Resnet9.pth', map_location=get_default_device()))

    out = model(img2tensor(x)[None])
    scaled = softmax(out)
    prob = torch.max(scaled).item()
    label = classes[torch.argmax(scaled).item()]
    return {'label': label, 'probability': prob}

def predict_return(f_path): # f_path가 인자로 들어와야하는 함수
    face_cascade = cv2.CascadeClassifier('/Users/HyeonJae/nhj/final_project/final_web/Web/EmotionalClassificationWeb/back_flask/haarcascade_frontalface_default.xml')

    frame = cv2.imread(f_path) # f_path 경로에 있는 애를 불러와서 이미지로 받아서 프레임으로 넣어줌. // 이미지상태로 cv상태로 쓸 수 있게 읽어오는 형식

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY) # 데이터 변환
    faces = face_cascade.detectMultiScale(gray, 1.3, 5) # 스케일 조정

    for (x,y,w,h) in faces:
        cv2.rectangle(frame, (x,y), (x+w,y+h), (0,255,0), 2) # 사각형 치고

        # takes the region of interest of the face only in gray
        roi_gray = gray[y:y+h, x:x+h]
        roi_color = frame[y:y+h, x:x+w]
        resized = cv2.resize(roi_gray, (48, 48)) # 리사이즈 

        img = img2tensor(resized) # 텐서값으로 바꿔줘라 다시

        prediction = predict(img) # 위의 predict() 를 사용
        return prediction # 라벨, 프로바빌리티 형식
    # cv2.putText(img, f"{prediction['label']}", (x, y-10), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,255,0))


# img = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
# plt.figure()
# plt.imshow(img)
# plt.show()