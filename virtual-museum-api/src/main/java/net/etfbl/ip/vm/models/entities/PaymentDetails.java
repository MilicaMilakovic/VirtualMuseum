package net.etfbl.ip.vm.models.entities;

public class PaymentDetails {
        private int userId;
        private int tourId;

        private String cardholder;
        private String cardNumber;
        private String expDate;
        private String type;
        private String pin;

        public PaymentDetails() {
            // TODO Auto-generated constructor stub

        }

        public PaymentDetails(int userId, int tourId, String cardholder, String cardNumber, String expDate, String type,
                              String pin) {
            super();
            this.userId = userId;
            this.tourId = tourId;
            this.cardholder = cardholder;
            this.cardNumber = cardNumber;
            this.expDate = expDate;
            this.type = type;
            this.pin = pin;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getTourId() {
            return tourId;
        }

        public void setTourId(int tourId) {
            this.tourId = tourId;
        }

        public String getCardholder() {
            return cardholder;
        }

        public void setCardholder(String cardholder) {
            this.cardholder = cardholder;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getExpDate() {
            return expDate;
        }

        public void setExpDate(String expDate) {
            this.expDate = expDate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        @Override
        public String toString() {
            return "PaymentDetails [userId=" + userId + ", tourId=" + tourId + ", cardholder=" + cardholder
                    + ", cardNumber=" + cardNumber + ", expDate=" + expDate + ", type=" + type + ", pin=" + pin + "]";
        }


}
