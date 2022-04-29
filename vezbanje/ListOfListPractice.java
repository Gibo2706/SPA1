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

        //add mail
        public void addMail(String sender, String recipient, String subject, String body) {
            Mail mail = new Mail(sender, recipient, subject, body, this);
            this.next = mail;
        }

        public String toString() {
            String rez = "Mail [";
            rez += sender + " " + recipient + " " + subject + " " + body;
            return rez + "]";
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

    public void deleteMail(String sender, String recipient, String subject, String body) {  // delete mail with given sender, recipient, subject and body   
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.recipient.equalsIgnoreCase(recipient) && cur.subject.equalsIgnoreCase(subject) && cur.body.equalsIgnoreCase(body)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }
    

    public void printMailList() {
        Mail cur = this.first;
        while (cur != null) {
            cur.printMail();
            cur = cur.next;
        }
    }

    // delete mail by phone number
    public void deleteMailByPhoneNumber(String phoneNumber) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(phoneNumber) || cur.recipient.equalsIgnoreCase(phoneNumber)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailBySubject(String subject) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.subject.equalsIgnoreCase(subject)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailByBody(String body) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.body.equalsIgnoreCase(body)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailBySender(String sender) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailByRecipient(String recipient) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.recipient.equalsIgnoreCase(recipient)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailBySenderAndRecipient(String sender, String recipient) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.recipient.equalsIgnoreCase(recipient)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailBySenderAndSubject(String sender, String subject) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.subject.equalsIgnoreCase(subject)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    public void deleteMailBySenderAndBody(String sender, String body) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.body.equalsIgnoreCase(body)) {
                if (prev == null) {
                    this.first = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
    }

    //add mail (Mail)
    public void addMail(Mail mail) {
        Mail cur = this.first;
        Mail prev = null;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(mail.sender) && cur.recipient.equalsIgnoreCase(mail.recipient) && cur.subject.equalsIgnoreCase(mail.subject) && cur.body.equalsIgnoreCase(mail.body)) {
                Svetovid.out.println("Mail vec postoji!");
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        if (prev == null) {
            this.first = mail;
        } else {
            prev.next = mail;
        }
    }

    // forward mail to another recipient
    public void forwardMail(String sender, String recipient, String subject, String body) {
        Mail cur = this.first;
        while (cur != null) {
            if (cur.sender.equalsIgnoreCase(sender) && cur.recipient.equalsIgnoreCase(recipient) && cur.subject.equalsIgnoreCase(subject) && cur.body.equalsIgnoreCase(body)) {
                Mail mail = new Mail(cur.sender, cur.recipient, cur.subject, cur.body, null);
                this.addMail(mail);
                return;
            }
            cur = cur.next;
        }
        Svetovid.out.println("Nije pronadjen mail!");
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