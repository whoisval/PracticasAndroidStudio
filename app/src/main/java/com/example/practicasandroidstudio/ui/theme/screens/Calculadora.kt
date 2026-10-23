package com.example.practicasandroidstudio.ui.theme.screens

import java.math.BigDecimal
import java.math.MathContext

class Calculadora {

    var texto: String = "0"
        private set

    private var acumulado: BigDecimal? = null

    private var operador: String? = null

    private var nuevaEntrada = true

    private val precision = MathContext(16)

    fun pulsar(tecla: String) {

        if (tecla == "AC") {
            limpiar()
            return
        }

        if (texto == "Error") {

            limpiar()

            if (tecla !in "0".."9" && tecla != ".") {
                return
            }
        }

        try {

            when (tecla) {

                in "0".."9" -> {

                    if (nuevaEntrada || texto == "0") {

                        texto = tecla

                    } else if (texto.count { it.isDigit() } < 16) {

                        texto += tecla
                    }

                    nuevaEntrada = false
                }

                "." -> {

                    if (nuevaEntrada) {
                        texto = "0"
                    }

                    if (!texto.contains(".")) {
                        texto += "."
                    }

                    nuevaEntrada = false
                }

                "<" -> {

                    if (!nuevaEntrada) {

                        texto = texto.dropLast(1)

                        if (
                            texto.isEmpty() ||
                            texto == "-"
                        ) {
                            texto = "0"
                        }
                    }
                }

                "±" -> {

                    if (nuevaEntrada && operador != null) {
                        texto = "0"
                    }

                    texto =
                        if (texto.startsWith("-")) {

                            texto.drop(1)

                        } else if (texto != "0") {

                            "-$texto"

                        } else {

                            "0"
                        }

                    nuevaEntrada = false
                }

                "%" -> {

                    texto = formatear(
                        valor().divide(
                            BigDecimal("100"),
                            precision
                        )
                    )

                    nuevaEntrada = false
                }

                "+", "-", "x", "÷" -> {

                    if (
                        operador != null &&
                        !nuevaEntrada
                    ) {
                        resolver()
                    }

                    acumulado = valor()

                    operador = tecla

                    nuevaEntrada = true
                }

                "=" -> {

                    if (
                        operador != null &&
                        !nuevaEntrada
                    ) {
                        resolver()
                    }

                    operador = null

                    acumulado = null

                    nuevaEntrada = true
                }
            }

        } catch (_: ArithmeticException) {

            error()

        } catch (_: NumberFormatException) {

            error()
        }
    }

    private fun resolver() {

        val a = acumulado ?: return

        val b = valor()

        val resultado = when (operador) {

            "+" ->
                a.add(b, precision)

            "-" ->
                a.subtract(b, precision)

            "x" ->
                a.multiply(b, precision)

            "÷" -> {

                if (b.compareTo(BigDecimal.ZERO) == 0) {

                    throw ArithmeticException(
                        "No se puede dividir entre cero"
                    )
                }

                a.divide(b, precision)
            }

            else ->
                b
        }

        texto = formatear(resultado)

        acumulado = resultado
    }

    private fun valor(): BigDecimal {

        val valorTexto =
            if (texto.endsWith(".")) {

                texto.dropLast(1)

            } else {

                texto
            }

        return BigDecimal(valorTexto)
    }

    private fun formatear(
        numero: BigDecimal
    ): String {

        if (
            numero.compareTo(BigDecimal.ZERO) == 0
        ) {
            return "0"
        }

        val normalizado =
            numero.stripTrailingZeros()

        val plano =
            normalizado.toPlainString()

        return if (plano.length <= 22) {

            plano

        } else {

            normalizado.toString()
        }
    }

    private fun limpiar() {

        texto = "0"

        acumulado = null

        operador = null

        nuevaEntrada = true
    }

    private fun error() {

        limpiar()

        texto = "Error"
    }

    fun guardar(): List<String> {

        return listOf(
            texto,
            acumulado?.toString() ?: "",
            operador ?: "",
            nuevaEntrada.toString()
        )
    }

    fun restaurar(
        estado: List<String>
    ) {

        if (estado.size != 4) {
            return
        }

        texto = estado[0]

        acumulado =
            estado[1]
                .takeIf {
                    it.isNotEmpty()
                }
                ?.toBigDecimalOrNull()

        operador =
            estado[2]
                .takeIf {
                    it.isNotEmpty()
                }

        nuevaEntrada =
            estado[3].toBoolean()
    }
}