package shortestRoute;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DesignTool extends JFrame {

	private JPanel contentPane;
	Boolean state = false;
	private JTextField textField1;
	private JTextField textField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesignTool frame = new DesignTool();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public DesignTool() {
		setTitle("Shortest Route");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		BoardView boardView = new BoardView();
		boardView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boardView.mouseClicked(e.getPoint());
			}
		});
		contentPane.add(boardView, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(10, 20));
		contentPane.add(panel_1, BorderLayout.NORTH);

		textField1 = new JTextField();
		panel_1.add(textField1);
		textField1.setColumns(20);

		textField2 = new JTextField();
		panel_1.add(textField2);
		textField2.setColumns(20);
		textField2.setText("PLS INPUT KEY(number)");

		Button Solve = new Button("Solve");
		Solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField1.setText("");
				textField1.setText("Shortest Distance is " + Double.toString(boardView.solve()));
			}
		});
		panel.add(Solve);

		Button Reset = new Button("Reset");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardView.reset();
			}
		});
		panel.add(Reset);

		JButton SAVE = new JButton("SAVE");
		SAVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filename;
				try {
					filename = Integer.parseInt(textField2.getText());
					boardView.save(filename);
				} catch (NumberFormatException e1) {
					textField2.setText("Error INTUTE number !!");
				}

			}
		});
		panel.add(SAVE);

		JButton LOAD = new JButton("LOAD");
		LOAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filename;
				int temp;
				try {
					filename = Integer.parseInt(textField2.getText());
					temp = boardView.load(filename);
					textField2.setText("");
					textField2.setText(Integer.toString(temp));
				} catch (NumberFormatException e1) {
					textField2.setText("Error INTUTE number !!");
				}

			}
		});
		panel.add(LOAD);

	}

}
