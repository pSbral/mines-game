package visao

import modelo.Campo
import modelo.CampoEvento
import java.awt.Color
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.SwingUtilities

private val COR_BG_NORMAL = Color(103, 127, 127)
private val COR_BG_MARCACAO = Color(255, 214, 33)
private val COR_BG_EXPLOSAO = Color(189, 66, 68)

class BotaoCampo(private val campo: Campo) : JButton() {

    init {
        background = COR_BG_NORMAL
        isOpaque = true
        border = BorderFactory.createBevelBorder(0)
        addMouseListener(MouseCliqueListener(campo, { it.abrir() }, { it.alterarMarcacao() }))

        campo.onEvento(this::aplicarEstilo)
    }

    private fun aplicarEstilo(campo: Campo, evento: CampoEvento) {
        when(evento) {
            CampoEvento.EXPLOSAO -> aplicarEstiloExplodido()
            CampoEvento.ABERTURA -> aplicarEstiloAberto()
            CampoEvento.MARCACAO -> aplicarEstiloMarcado()
            else -> aplicarEstiloPadrao()
        }

        SwingUtilities.invokeLater {
            repaint()
            validate()
        }
    }

    private fun aplicarEstiloExplodido() {
        background = COR_BG_EXPLOSAO
        text = "\uD83D\uDCA3"
    }

    private fun aplicarEstiloAberto() {
        background = COR_BG_NORMAL
        border = BorderFactory.createLineBorder(Color(111, 173, 172))

        foreground = when (campo.qtdeVizinhosMinados) {
            1 -> Color(101, 227, 71)
            2 -> Color(68, 199, 255)
            3 -> Color(255, 111, 48)
            4, 5, 6 -> Color.RED
            else -> Color.PINK
        }

        //9 & 10. Alteração na lógica do código para mostrar o numero de bombas, mostrando um ícone diferente ao mostrar uma bomba | LÓGICA
        text = when (campo.qtdeVizinhosMinados) {

            1 -> "\uD83D\uDEBD" // Privada
            2 -> "\uD83D\uDC09" // Dragão
            3 -> "\uD83C\uDF50" // Pera
            4, 5, 6 -> "?"
            else -> ""
        }
    }

    private fun aplicarEstiloMarcado() {
        background = COR_BG_MARCACAO
        foreground = Color.WHITE
        text = "\uD83D\uDEA9"
    }

    private fun aplicarEstiloPadrao() {
        background = COR_BG_NORMAL
        border = BorderFactory.createBevelBorder(0)
        text = ""
    }
}