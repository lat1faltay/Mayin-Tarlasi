package mayinTarlasi;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MayinTarlasi implements MouseListener {
	JFrame frame;
	Btn[][] board = new Btn[10][10];
	
	public MayinTarlasi(){
		frame = new JFrame("Mayın Tarlası");
		
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,10));
		frame.setResizable(false);
		
		for(int row=0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				Btn b = new Btn(row,col);
				frame.add(b);
				b.addMouseListener(this);
				board[row][col] = b;
			}
		}
		
		generatedMine();
		updateCount();
		//print();
		
	}
	
	public void generatedMine() {
		int i = 0;
		while(i < 10) {
			int randRow = (int)(Math.random() * board.length);
			int randCol = (int)(Math.random() * board[0].length);
			
			while(board[randRow][randCol].isMine()) {
				randRow = (int)(Math.random() * board.length);
				randCol = (int)(Math.random() * board[0].length);
			}
			board[randRow][randCol].setMine(true);
			i++;
			
		}
	}
	
	public void print() {
		for(int row=0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("C:\\Development\\MayinTarlasi\\src\\mayinTarlasi\\mine.png"));
				}else {
					board[row][col].setText(board[row][col].getCount()+"");
				}
			}
		}
	}

	
	public void updateCount() {
		for(int row=0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMine()) {
					counting(row,col);
				}
			}
		}
	}
	
	public void counting(int row, int col) {
		for(int i = row - 1; i <= row+1; i++) {
			for(int k=col-1; k<=col+1; k++ ) {
				try {
					int value = board[i][k].getCount();
					board[i][k].setCount(++value);
				}catch(Exception e){
					
				}
			}
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Btn b = (Btn) e.getComponent();
		
		if(e.getButton() == 1) {
			System.out.println("sol tık");
			if(b.isMine() == true) {
				JOptionPane.showMessageDialog(frame, "Game Over!");
			}else {
				
			}
			
		}else if(e.getButton() == 3) {
			System.out.println("sağ tık");
			if(!b.isFlag()) {
				b.setIcon(new ImageIcon("C:\\Development\\MayinTarlasi\\src\\mayinTarlasi\\flag.png"));
				b.setFlag(true);
			}else {
				b.setIcon(null);
				b.setFlag(false);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
