package visao

import modelo.Tabuleiro
import modelo.TabuleiroEvento
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    TelaPrincipal()
}

class TelaPrincipal : JFrame() {

    //3. Alteração no valor de qtdeLinhas durante a criação do tabuleiro | QUANTIDADE
    //4. Alteração no valor de qtdeColunas durante a criação do tabuleiro | QUANTIDADE
    private val tabuleiro = Tabuleiro(qtdeLinhas = 20, qtdeColunas = 20, qtdeMinas = 40)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)

    init {
        tabuleiro.onEvento(this::mostrarResultado)
        add(painelTabuleiro)

        iconImage = ImageIcon("D:\\CampoMinadoo\\src\\midia\\icon.png").image

        //5. Alteração no tamanho da janela do aplicativo | DISPLAY
        setSize(500, 500)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        //6. Alteração no nome da janela do aplicativo | DISPLAY
        title = "Video Gamer"
        isVisible = true
    }

    private fun mostrarResultado(evento: TabuleiroEvento) {
        SwingUtilities.invokeLater {
            val msg = when(evento) {
                //7. Alteração na mensagem de vitória | MENSAGEM
                TabuleiroEvento.VITORIA -> "Parabéns brother és um vencedor!"
                //8. Alteração na mensagem de derrota | MENSAGEM
                TabuleiroEvento.DERROTA -> "Recebeu uma diferença..."
            }

            JOptionPane.showMessageDialog(this, msg)
            tabuleiro.reiniciar()

            painelTabuleiro.repaint()
            painelTabuleiro.validate()
        }
    }
}