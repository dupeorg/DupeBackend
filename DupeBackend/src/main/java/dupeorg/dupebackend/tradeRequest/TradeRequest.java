package dupeorg.dupebackend.tradeRequest;

public class TradeRequest {

    private String sender_id;
    private String sender_item;
    private String receiver_id;
    private String receiver_item;

    public TradeRequest(String sender_id, String sender_item, String receiver_id, String receiver_item) {
        this.sender_id = sender_id;
        this.sender_item = sender_item;
        this.receiver_id = receiver_id;
        this.receiver_item = receiver_item;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getSender_item() {
        return sender_item;
    }

    public void setSender_item(String sender_item) {
        this.sender_item = sender_item;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getReceiver_item() {
        return receiver_item;
    }

    public void setReceiver_item(String receiver_item) {
        this.receiver_item = receiver_item;
    }
}
