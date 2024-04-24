import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        // Định dạng ngày
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // In ra ngày hiện tại
        System.out.println(format.format(calendar.getTime()));

        // Trừ đi 7 ngày
        calendar.add(Calendar.DATE, -7);

        // In ra ngày 7 ngày trước
        System.out.println(format.format(calendar.getTime()));
    }
}
