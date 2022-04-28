class ListOfListPractice {
    public static void main(String[] args) {
        MailList lista = new MailList();
        // test string
        // sender: sender1, sender2, sender3
        // receiver: receiver1, receiver2, receiver3
        // message: message1, message2, message3
        // subject: subject1, subject2, subject3
        // mail: null, null, null
        lista.insertMail("sender1", "receiver1", "message1", "subject1");
        lista.insertMail("sender2", "receiver2", "message2", "subject2");
        lista.insertMail("sender3", "receiver3", "message3", "subject3");
        Svetovid.out.println(lista);

    }
}

class MailList {
    class Mail {
        String sender;
        String recipient;
        String subject;
        String body;
        Mail next;
        
        public Mail(String sender, String recipient, String subject, String body, Mail next) {
            this.sender = sender;
            this.recipient = recipient;
            this.subject = subject;
            this.body = body;
            this.next = next;
        }

        public String toString() {
            String rez = "Mail ";
            rez += sender + " " + recipient + " " + subject + " " + body;
            return rez;
        }

        public void printMail() {
            Svetovid.out.println(this);
        }
    }

    Mail first;

    public MailList() {
        this.first = null;
    }

    public void insertMail(String sender, String recipient, String subject, String body) {
        Mail cur = this.first;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.recipient.equalsIgnoreCase(recipient) && cur.subject.equalsIgnoreCase(subject) && cur.body.equalsIgnoreCase(body)) {
                Svetovid.out.println("Vec postoji mail!");
                return;
            }
            cur = cur.next;
        }
        Mail novi = new Mail(sender, recipient, subject, body, null);
        novi.next = this.first;
        this.first = novi;
    }

    public String isThereMail(String sender, String recipient, String subject, String body) {
        Mail cur = this.first;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.recipient.equalsIgnoreCase(recipient) && cur.subject.equalsIgnoreCase(subject) && cur.body.equalsIgnoreCase(body)) {
                return cur.toString();
            }
            cur = cur.next;
        }
        return null;
    }

    public String toString() {
        String rez = "Mailovi [";
        Mail cur = this.first;
        while (cur != null) {
            rez += cur.toString() + " ";
            cur = cur.next;
        }
        rez += "]";
        return rez;
    }
}