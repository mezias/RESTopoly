package resttopoly;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class Answer
{
//    public static final int OK = 200;
//    public static final int BAD_REQUEST = 400;

    private int code;
    private String body;

    public Answer(int code, String body)
    {
        this.code = code;
        this.body = body;
    }

    public Answer(int code)
    {

        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public String getBody()
    {
        return body;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (code != answer.code) return false;
        return body != null ? body.equals(answer.body) : answer.body == null;

    }

    @Override
    public int hashCode()
    {
        int result = code;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
