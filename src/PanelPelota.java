import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class PanelPelota extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	// Positions on X and Y for the ball, player 1 and player 2
	private int pelotaX = 10, pelotaY = 100, pelota2X = 230, pelota2Y = 100, jug1X = 10, jug1Y = 100, jug2X = 230, jug2Y = 100;
	Thread hilo;
	int derecha = 5;// to the right
	int izquierda = -5; // to the left
	int arriba = 5; // upward
	int abajo = -5; // down
	int ancho, alto; // Width and height of the ball
	int ancho2, alto2;
	// Scores
	int contPlay1 = 0, contPlay2 = 0;
	boolean player1FlagArr, player1FlagAba, player2FlagArr, player2FlagAba;
	boolean juego, gameOver;

	public PanelPelota() {
		juego = true;
		hilo = new Thread(this);
		hilo.start();
	}

	// Draw ball and ships
	public void paintComponent(Graphics gc) {
		setOpaque(false);
		super.paintComponent(gc);

		// Draw ball
		gc.setColor(Color.black);
		gc.fillOval(pelotaX, pelotaY, 8, 8);
		gc.fillOval(pelota2X, pelota2Y, 8, 8);

		// Draw ships
		gc.fillRect(jug1X, jug1Y, 10, 25);
		gc.fillRect(jug2X, jug2Y, 10, 25);

		// Draw scores
		gc.drawString("Jugador1: " + contPlay1, 25, 10);
		gc.drawString("Jugador2: " + contPlay2, 150, 10);

		if (gameOver) {
			//gc.drawString("Game Over", 100, 125);
			Restart replay = new Restart();
			JPanel midPanel = new JPanel();
			replay.setVisible(true);
			if(contPlay1 == 6)
				midPanel.add(new JLabel("Player1 wins!"));
			if(contPlay2 == 6)
				midPanel.add(new JLabel("Player2 wins!"));
			replay.setTitle("Game Over!");
			replay.setSize(300, 100);
			replay.setLocationRelativeTo(null);
			
			midPanel.add(new JLabel(""));
			replay.add(midPanel, BorderLayout.CENTER);
			replay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	// Positions on X and Y for the ball
	public void dibujarPelota(int nx, int ny) {
		pelotaX = nx;
		pelotaY = ny;
		this.ancho = this.getWidth();
		this.alto = this.getHeight();
		repaint();
	}
	
	public void dibujarPelota2(int nx, int ny) {
		pelota2X = nx;
		pelota2Y = ny;
		this.ancho2 = this.getWidth();
		this.alto2 = this.getHeight();
		repaint();
	}

	// Here we receive from the game container class the key pressed
	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		// Move ship 1
		case KeyEvent.VK_W:
			player1FlagArr = true;
			break;
		case KeyEvent.VK_S:
			player1FlagAba = true;
			break;

		// Move ship 2
		case KeyEvent.VK_UP:
			player2FlagArr = true;
			break;
		case KeyEvent.VK_DOWN:
			player2FlagAba = true;
			break;
		}
	}

	// Here we receive from the game container class the key released
	public void keyReleased(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		// Mover Nave1
		case KeyEvent.VK_W:
			player1FlagArr = false;
			break;
		case KeyEvent.VK_S:
			player1FlagAba = false;
			break;

		// Mover nave 2
		case KeyEvent.VK_UP:
			player2FlagArr = false;
			break;
		case KeyEvent.VK_DOWN:
			player2FlagAba = false;
			break;
		}
	}

	// Move player 1
	public void moverPlayer1() {
		if (player1FlagArr == true && jug1Y >= 0)
			jug1Y += abajo;
		if (player1FlagAba == true && jug1Y <= (this.getHeight() - 25))
			jug1Y += arriba;
		dibujarPlayer1(jug1X, jug1Y);
	}

	// Move player 2
	public void moverPlayer2() {
		if (player2FlagArr == true && jug2Y >= 0)
			jug2Y += abajo;
		if (player2FlagAba == true && jug2Y <= (this.getHeight() - 25))
			jug2Y += arriba;
		dibujarPlayer2(jug2X, jug2Y);
	}

	// Position on Y for the player 1
	public void dibujarPlayer1(int x, int y) {
		this.jug1X = x;
		this.jug1Y = y;
		repaint();
	}

	// Position on Y for the player 2
	public void dibujarPlayer2(int x, int y) {
		this.jug2X = x;
		this.jug2Y = y;
		repaint();
	}

	public void run() {
		// TODO Auto-generated method stub
		boolean izqDer = false;
		boolean arrAba = false;
		boolean izqDer2 = false;
		boolean arrAba2 = false;

		while (true) {

			if (juego) {

				// The ball move from left to right
				if (izqDer) {
					// a la derecha
					pelotaX += derecha;
					if (pelotaX >= (ancho - 8))
						izqDer = false;
				} else {
					// a la izquierda
					pelotaX += izquierda;
					if (pelotaX <= 0)
						izqDer = true;
				}

				// The ball moves from up to down
				if (arrAba) {
					// hacia arriba
					pelotaY += arriba;
					if (pelotaY >= (alto - 8))
						arrAba = false;

				} else {
					// hacia abajo
					pelotaY += abajo;
					if (pelotaY <= 0)
						arrAba = true;
				}
				dibujarPelota(pelotaX, pelotaY);
				
				// The second ball move from left to right
				if (izqDer2) {
					// a la derecha
					pelota2X += derecha;
					if (pelota2X >= (ancho - 8))
						izqDer2 = false;
				} else {
					// a la izquierda
					pelota2X += izquierda;
					if (pelota2X <= 0)
						izqDer2 = true;
				}

				// The ball moves from up to down
				if (arrAba2) {
					// hacia arriba
					pelota2Y += arriba;
					if (pelota2Y >= (alto - 8))
						arrAba2 = false;

				} else {
					// hacia abajo
					pelota2Y += abajo;
					if (pelota2Y <= 0)
						arrAba2 = true;
				}
				dibujarPelota2(pelota2X, pelota2Y);

				// Delay
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex) {

				}

				// Move player 1
				moverPlayer1();

				// Move player 2
				moverPlayer2();

				// The score of the player 1 increase
				if (pelotaX >= (ancho - 8) || pelota2X >= (ancho2 - 8))
					contPlay1++;

				// The score of the player 2 increase
				if (pelotaX == 0 || pelota2X == 0)
					contPlay2++;

				// Game over. Here you can change 6 to any value
				// When the score reach to the value, the game will end
				if (contPlay1 == 6 || contPlay2 == 6) {
					juego = false;
					gameOver = true;
				}

				// The ball stroke with the player 1
				if (pelotaX == jug1X + 10 && pelotaY >= jug1Y && pelotaY <= (jug1Y + 25))
					izqDer = true;

				// The ball stroke with the player 2
				if (pelotaX == (jug2X - 5) && pelotaY >= jug2Y && pelotaY <= (jug2Y + 25))
					izqDer = false;
				
				// The ball2 stroke with the player 1
				if (pelota2X == jug1X + 10 && pelota2Y >= jug1Y && pelota2Y <= (jug1Y + 25))
					izqDer2 = true;

				// The ball2 stroke with the player 2
				if (pelota2X == (jug2X - 5) && pelota2Y >= jug2Y && pelota2Y <= (jug2Y + 25))
					izqDer2 = false;
			}
		}
	}
}