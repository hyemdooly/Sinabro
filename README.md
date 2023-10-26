# 남북한단어변환 안드로이드 앱 ‘시나브로’
제 3회 동국 SW공모대전 개인 프로젝트

남한/북한 단어 입력 시 북한/남한 단어를 출력, 북한 사전 제공 서비스

## 프로젝트 정보

### 프로젝트 기간
2018.10

### 기술 스택
Android, Kotlin, Python, Gson, Firebase-Cloud Firestore

## 앱 스크린샷
<img src="https://github.com/hyemdooly/Sinabro/assets/15646373/2fa06e05-ff6f-4f7d-8e5e-a2d5e5248983" width="200"/> <img src="https://github.com/hyemdooly/Sinabro/assets/15646373/fd760e6c-0162-4092-a25e-10885e61b7bf" width="200"/> <img src="https://github.com/hyemdooly/Sinabro/assets/15646373/bd17dbe6-4795-4e39-abba-11c87e3015d1" width="200"/> <img src="https://github.com/hyemdooly/Sinabro/assets/15646373/30b24446-43c8-4a5d-855e-8e3ce4723afe" width="200"/>

## 개발 내용
### CSV 크롤링
<img src="https://github.com/hyemdooly/Sinabro/assets/15646373/3b0bdf2d-5236-4486-a082-0f2277ee2bcf" width="500" />

통일부 북한자료센터에서 가져온 단어 Data의 양이 많아 수동으로 입력할 수 없다고 판단했습니다. 따라서 Python으로 크롤링 후 Cloud Firestore API를 사용하여 Data를 모두 자동으로 업로드했습니다.

