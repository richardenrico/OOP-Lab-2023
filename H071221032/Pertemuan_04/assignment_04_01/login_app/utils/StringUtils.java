package login_app.utils;

public class StringUtils {
    public static String nickName(String fullName) {
        String[] names = fullName.split(" ");
        if(names.length>1) {
            return names[1];
        }
        else {
            return fullName;
        }
    }
    /*
     TODO
     * Buatlah sebuah method static yang akan
     * mengembalikan nickName berdasarkan fullName yang diberikan
     * aturan pembuatan nickName adalah :
     * 1. Jika fullName hanya 1 kata maka nickName = fullName
     * (ex: FullName = Agus, maka NickName = Agus)
     * 2. Jika fullName lebih dari 1 kata maka nickName adalah kata kedua dari
     * fullName
     * (ex: FullName = Eurico Devon, maka NickName = Devon)
     */
}
