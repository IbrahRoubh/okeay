package metin2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class label{
    JFrame frame;
    JLabel label;
    static int score =0;
    JButton addBtn;
    JButton[] buttonHolder=new JButton[3];
    JButton[] pickBtn =new  JButton[3];
    JButton[] mainBtn =new JButton[5];
    ArrayList<String> cards=new ArrayList<>();
    String[] cardsNameArr ={"1 r","2 r","3 r","4 r","5 r","6 r","7 r","8 r"
            ,"1 y","2 y","3 y","4 y","5 y","6 y","7 y","8 y"
            ,"1 b","2 b","3 b","4 b","5 b","6 b","7 b","8 b"};
    String s=Integer.toString(score);

    public label() {
        frame = new JFrame("okay");
        label=new JLabel();
        addBtn = new JButton("add carts");

        setMainBtn(mainBtn);
        setPickBtn(pickBtn);
        setAddBtn(addBtn,mainBtn);
        setCards();

        //score label
        label.setText(s);
        label.setBackground(Color.pink);
        label.setBounds(280,300,30,10);

        //Initialize frame and add all buttons and label
        frame.setLayout(null);
        frame.add(mainBtn[0]);
        frame.add(mainBtn[1]);
        frame.add(mainBtn[2]);
        frame.add(mainBtn[3]);
        frame.add(mainBtn[4]);
        frame.add(addBtn);
        frame.add(pickBtn[0]);
        frame.add(pickBtn[1]);
        frame.add(pickBtn[2]);
        frame.add(label);
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(350, 180);
        frame.setResizable(false);
    }
    public void setCards(){
        for(int i=0;i<24;i++) {
            cards.add(cardsNameArr[i]);
        }
    }
    public boolean transfer(JButton b) {
        for(int i=0;i<3;i++)
        {
            if(pickBtn[i].getText().equalsIgnoreCase(""))
            {
                pickBtn[i].setText(b.getText());
                pickBtn[i].setBackground(b.getBackground());
                pickBtn[i].setEnabled(true);
                buttonHolder[i]=b;
                if(pickBtn[0].isEnabled()&& pickBtn[1].isEnabled()&& pickBtn[2].isEnabled())
                {
                    if(IsConsecutive()||(pickBtn[0].getText().equalsIgnoreCase(pickBtn[1].getText())&& pickBtn[1].getText().equalsIgnoreCase(pickBtn[2].getText())))
                 {
                     score = score +100;
                     String s=Integer.toString(score);
                     label.setText(s);
                     setPickBtnDefault();
                 }
                }
                return true;
            }
        }
        return false;
    }
    public void setMainBtn(JButton[] b){
        int x=20;// to set buttons x location

        //set all main buttons to default state
        for(int i=0;i<5;i++)
        {
            b[i]=new JButton();
            b[i].setEnabled(false);
            b[i].setSize(80,100);
            b[i].setVisible(true);
            b[i].setBackground(Color.gray);
            b[i].setFont(new Font("Arial", Font.PLAIN, 40));
            b[i].setLocation(x,20);
            setMainBtnAction(b[i]);
            setRightMouesAction(b[i]);
            x=x+115;
        }
    }
    public void setRightMouesAction(JButton b) {
        b.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(SwingUtilities.isRightMouseButton(e)&&b.isEnabled())
                        {
                            int input=JOptionPane.showConfirmDialog(null,"you will delete this card,are you sure?","card delete!!",JOptionPane.YES_NO_OPTION);
                            if(input==0)
                            {
                                b.setEnabled(false);
                                b.setBackground(Color.GRAY);
                                b.setText("");
                            }
                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                }
        );
    }
    public void setMainBtnAction(JButton b) {
        b.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(transfer(b))
                            b.setEnabled(false);
                    }
                }
        );
    }
    public void setPickBtn(JButton[] b){
        int x2=100;// to set buttons x location

        //set all puck buttons to default state
        for(int i=0;i<3;i++)
        {
            b[i]=new JButton();
            b[i].setSize(80, 100);
            b[i].setVisible(true);
            b[i].setLocation(x2, 150);
            b[i].setEnabled(false);
            b[i].setFont(new Font("Arial", Font.PLAIN, 40));
            setPickBtnAction(b[i],i);
            x2=x2+150;
        }
    }
    public void setPickBtnAction(JButton b,int index){
        b.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonHolder[index].setEnabled(true);
                        b.setEnabled(false);
                        b.setText("");
                        b.setBackground(Color.WHITE);
                    }
                }
        );
    }
    public void setAddBtn(JButton b,JButton[] mBtn){
        b.setSize(80, 50);
        b.setVisible(true);
        b.setLocation(50, 280);
        b.setText("Add");
        b.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(int i=0;i<5;i++)
                        {
                            if(mBtn[i].getText().equalsIgnoreCase(""))
                            {
                                pickOut(mBtn[i]);
                            }
                        }
                    }
                }
        );
    }
    public void pickOut(JButton b) {
        Random random=new Random();
        int x=random.nextInt(cards.size());
        String s=cards.get(x);
        cards.remove(x);
        String[] item=s.split(" ");

        b.setText(item[0]);
        if(item[1].equalsIgnoreCase("r"))
            b.setBackground(Color.red);
        else if(item[1].equalsIgnoreCase("b"))
            b.setBackground(Color.blue);
        else if(item[1].equalsIgnoreCase("y"))
            b.setBackground(Color.yellow);
        b.setEnabled(true);
    }
    public void setPickBtnDefault() {
        buttonHolder[0].setText("");
        buttonHolder[0].setBackground(Color.gray);
        buttonHolder[1].setText("");
        buttonHolder[1].setBackground(Color.gray);
        buttonHolder[2].setText("");
        buttonHolder[2].setBackground(Color.gray);
        pickBtn[0].setBackground(Color.white);
        pickBtn[0].setText("");
        pickBtn[0].setEnabled(false);
        pickBtn[1].setBackground(Color.white);
        pickBtn[1].setText("");
        pickBtn[1].setEnabled(false);
        pickBtn[2].setBackground(Color.white);
        pickBtn[2].setText("");
        pickBtn[2].setEnabled(false);
    }
    public boolean IsConsecutive(){
        int temp;
        int n1=Integer.parseInt(pickBtn[0].getText());
        int n2=Integer.parseInt(pickBtn[1].getText());
        int n3=Integer.parseInt(pickBtn[2].getText());
        if(n1>n2)
        {
            temp=n1;
            n1=n2;
            n2=temp;
        }
        if(n2>n3)
        {
            temp=n2;
            n2=n3;
            n3=temp;
        }
        if(n1>n2)
        {
            temp=n1;
            n1=n2;
            n2=temp;
        }

        if(n1+1==n2&&n2+1==n3)
        {
            return  true;
        }
        return false;
    }
}