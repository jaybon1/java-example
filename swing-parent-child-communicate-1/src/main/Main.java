
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

// 예제
// 메인 창에서 다이얼로그에 데이터를 전송하고
// 다이얼로그창에서 메인창으로 다시 데이터를 전송하는 예제

// 좌석의 정보를 다이얼로그 객체에 넘겨주기 위한 클래스
class SeatInfoDTO {
    // 좌석번호
    private Integer seatId;

    public SeatInfoDTO(Integer seatId) {
        this.seatId = seatId;
    }

    // getter
    public Integer getSeatId() {
        return seatId;
    }

    // seatId를 변경할 일이 없기때문에 setter는 만들지 않음

}

// 메인창의 좌석 버튼을 커스텀하기 위해 JButton 상속받아서 사용
class SeatButton extends JButton {

    // 좌석번호
    private Integer seatId;

    public SeatButton(Integer x, Integer y, Integer width, Integer height, Integer seatId, MyApp app) {
        // JButton의 생성자를 호출
        super(seatId + "번 좌석");
        setBounds(x, y, width, height);
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 다이얼로그 창을 생성
                MyDialog dialog = new MyDialog("좌석 선택", true, new SeatInfoDTO(seatId), app);
                dialog.setVisible(true);
            }
        });

        this.seatId = seatId;
    }

    // getter
    public Integer getSeatId() {
        return seatId;
    }

    // seatId를 변경할 일이 없기때문에 setter는 만들지 않음

}

// 다이얼로그 창을 커스텀하기 위해 JDialog 상속받아서 사용
class MyDialog extends JDialog {

    // JDialog의 필수 정보를 받아오는 동시에 SeatInfo도 같이 받아옴
    public MyDialog(String title, boolean modal, SeatInfoDTO seatInfo, MyApp app) {
        // JDialog의 생성자를 호출
        super(app.getFrame(), title, modal);

        // JDialog의 기본 설정

        // 엑스버튼을 누르면 닫히는 기능
        // this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // 창을 닫으면 메인창의 라벨에 표시된 좌석번호를 지움
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                // 다이얼로그창을 닫을 때 메인창의 라벨에 표시된 좌석번호를 지움
                app.getLabel().setText("좌석을 선택하지 않았습니다.");
                MyDialog.this.dispose();

            }
        });
        this.setBounds(100, 100, 300, 200);
        this.setLocationRelativeTo(app.getFrame());
        this.setResizable(false);

        // 프레임에 패널 추가
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setContentPane(panel);

        // 패널에 라벨 추가
        // SeatInfo의 정보를 받아와서 라벨에 표시
        JLabel label = new JLabel(seatInfo.getSeatId() + "번 좌석 입니다.");
        label.setBounds(0, 0, 100, 30);
        panel.add(label);

        // 패널에 버튼 추가
        // 좌석 선택 버튼
        JButton button = new JButton("선택");
        button.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 버튼을 누르면 메인창의 라벨에 좌석번호를 표시
                app.getLabel().setText(seatInfo.getSeatId() + "번 좌석을 선택하셨습니다.");
                MyDialog.this.dispose();
            }
        });
        button.setBounds(10, 50, 100, 30);
        panel.add(button);
    }
}

class MyApp {

    // 메인 창 데이터
    // 메인 창의 데이터를 다이얼로그에 this로 전달하기 위해 사용
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    public MyApp() {

        // 메인창
        frame = new JFrame("Parent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

        // 프레임에 패널 추가
        panel = new JPanel();
        panel.setLayout(null);
        frame.getContentPane().add(panel, "Center");

        // 패널에 라벨 추가
        label = new JLabel("좌석을 선택하지 않았습니다.");
        label.setBounds(0, 0, 200, 30);
        panel.add(label);

        // 패널에 좌석 버튼 추가
        // this 자기자신을 전달하여 frame label 등을 사용가능하게 함
        SeatButton seatButton1 = new SeatButton(0, 50, 100, 30, 1, this);
        SeatButton seatButton2 = new SeatButton(0, 100, 100, 30, 2, this);
        panel.add(seatButton1);
        panel.add(seatButton2);

    }

    // getter
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    // 자식이 부모를 객체 자체를 변경할 일이 없으므로 setter는 만들지 않음
    // label.setText("좌석을 선택하지 않았습니다."); 이런식으로는 변경가능

}

// 메인 클래스
public class Main {

    public static void main(String[] args) throws Exception {
        // 앱을 실행한다
        new MyApp();
    }
}