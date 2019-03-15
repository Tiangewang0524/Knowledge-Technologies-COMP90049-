/**
 * Created by wtg on 29/8/17.
 */

public class Record {
    private String token;
    private String type;
    private String canonicalForm;

    public Record(){

    }

    public Record(String token, String type, String canonicalForm) {
        this.token = token;
        this.type = type;
        this.canonicalForm = canonicalForm;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCanonicalForm() {
        return canonicalForm;
    }

    public void setCanonicalForm(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        return ((Record) obj).getCanonicalForm().equals(canonicalForm)
                && ((Record) obj).getType().equals(type)
                && ((Record) obj).getToken().equals(token);
    }
}
