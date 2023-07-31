package chattap;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements ActionListener
{   
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    JTextField text1;
    JPanel a1;
    
    //For setting the message one below the another.
    static Box vertical = Box.createVerticalBox();
    
    //Constructor of server class.
    //As soon as object is created of server class we should see a frame.
    Server()
    {
        //In order to set the layout.
        f.setLayout(null);
        
        /*
        For header of the server side START
        */
        
        //To divide the surface above frame we use panel.
        JPanel p1 = new JPanel();   
        //For background color of the header.
        p1.setBackground(new Color(42,171, 238));
        //Set the panel above the frame.
        f.add(p1);
        //Pass the coordinates for the header because we have made setLayout as NULL.
        p1.setBounds(0, 0, 450, 70);
        
        /*
        For back icon START
        */
        //Actual Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));  
        //Scaled image
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        //Because we can't pass the i2 directly below.
        ImageIcon i3 = new ImageIcon(i2);  
        //We cant set the icon directly on the frame hence we pass it to JLabel.
        JLabel back = new JLabel(i3);
        //Pass on the coordinates for the JLabel.
        back.setBounds(5, 20, 25, 25);
        //For setbounds to work.
        p1.setLayout(null);
        //Add JLabel to the panel.
        p1.add(back);
        
        //Event handling : On clicking the arrow, window should be closed.
        back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e)
                {
                    System.exit(0);
                }
        });
        /*
        For back icon END
        */
        
        /*
        For video icon START
        */
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i5 = i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel video = new JLabel(i6);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        /*
        For video icon END
        */
         
        /*
        For phone icon START
        */
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i8 = i7.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel phone = new JLabel(i9);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        /*
        For phone icon END
        */
        
        /*
        For more icon START
        */
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i11 = i10.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel more = new JLabel(i12);
        more.setBounds(420, 20, 10, 25);
        p1.add(more);
        /*
        For more icon END
        */
        
        /*
        For name of the person START
        */
        JLabel name = new JLabel("Aakriti Singh");
        name.setBounds(50,20,150,30);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,25));
        p1.add(name);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(50,45,100,25);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,10));
        p1.add(status);
        /*
        For name of the person END
        */
        
        /*
        For header of the server side END
        */
        
        /* 
        For Chatting Area START
        */
        a1 = new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);
        /*
        For Chatting Area END
        */
        
        /* 
        For Typing Area START
        */
        text1 = new JTextField();
        text1.setBounds(5,655,310,40);
        text1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text1);
        
        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(42,171, 238));
        send.setForeground(Color.WHITE);
        
        //For event handling :- button
        send.addActionListener(this);
        
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(send);
        /*
        For Typing Area END
        */
        
        //To remove the header of the frame.
        f.setUndecorated(true);
        
        //For setting the size of the frame.
        f.setSize(450,700);
              
        //By default it is top left corner of the screen.
        //If we want to customize it we use this function.
        f.setLocation(200,50);
        
        //Pick the complete frame and set its background to white.
        f.getContentPane().setBackground(Color.WHITE);
        
        //In order to show the frame on the desktop.
        //By default its visibility is false we have to change it to true.
        f.setVisible(true);
    }
    
    //To format the background of message that has been sent.
    public static JPanel formatLabel(String msg)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + msg + "</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(42,171, 238));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        
        //To set the time when message has been sent.
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //For send button
        String msg = text1.getText(); 
        JPanel p2 = formatLabel(msg);
        a1.setLayout(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        a1.add(vertical,BorderLayout.PAGE_START);
    
        try {
            dout.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //To empty the textfield after send has been clicked.
        text1.setText("");
        
        
        //To reload when message has been sent.
        f.repaint();
        f.invalidate();
        f.validate();
    }

    public static void main(String[] args) {
      //Anonymous object of the server class.
      new Server();
      
      try
      {
          //Start the server.
          ServerSocket skt = new ServerSocket(6001);
          //To accept the messages infinitely.
          while(true)
          {
              Socket s = skt.accept();
              //To receive the messages.
              DataInputStream din = new DataInputStream(s.getInputStream());
              //To send the messages.
              dout = new DataOutputStream(s.getOutputStream());
          
              while(true)
              {
                  //To read the message.
                  String m = din.readUTF();
                  JPanel panel = formatLabel(m);
                  JPanel left = new JPanel(new BorderLayout());
                  left.add(panel,BorderLayout.LINE_START);          
                  vertical.add(left);
                  f.validate();
                  
              }
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}
