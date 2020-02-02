package com.ryusei_imamura.timecapsule;

public class Post {

        String message;
        String userName;

        public Post() {
        }

        public Post(String userName, String message) {
            this.userName = userName;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage() {
            this.message = message;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
}