package util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Validadores * Contém todos os métodos para validação de dados
 * informados pelo usuário.
 *
 * @since 26/08/2013
 * @version 1.0
 * @author Mateus Generoso
 *
 * @version 2.0 05/08/2014
 * @author Gabriel Arsênio da Silva
 */
public abstract class Validadores {

    /**
     * Método responsável por prencher os caracteres de sobra de uma string com
     * número zero.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 27/08/2014
     *
     * @param str
     * @param tamanhoDesejado
     * @return
     */
    public static String preencheStringNumerosAntes(String str, int tamanhoDesejado) {
        if (str == null) {
            str = "";
        }

        if (str.length() > tamanhoDesejado) {
            str = str.substring(0, tamanhoDesejado);

        } else if (str.length() < tamanhoDesejado) {
            for (int i = str.length(); i < tamanhoDesejado; i++) {
                str = "0" + str;
            }
        }
        return str;
    }

    /**
     * Método responsável por prencher os caracteres de sobra de uma string com
     * espaços antes do valor.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 27/08/2014
     *
     * @param str
     * @param tamanhoDesejado
     * @return
     */
    public static String preencheStringEspacosAntes(String str, int tamanhoDesejado) {
        if (str == null) {
            str = "";
        }

        if (str.length() > tamanhoDesejado) {
            str = str.substring(str.length() - tamanhoDesejado, str.length());

        } else if (str.length() < tamanhoDesejado) {
            for (int i = str.length(); i < tamanhoDesejado; i++) {
                str = " " + str;
            }
        }
        return str;
    }

    /**
     * Método responsável por prencher os caracteres de sobra de uma string com
     * espaços depois do valor.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 27/08/2014
     *
     * @param str
     * @param tamanhoDesejado
     * @return
     */
    public static String preencheStringEspacosDepois(String str, int tamanhoDesejado) {
        if (str == null) {
            str = "";
        }

        if (str.length() > tamanhoDesejado) {
            str = str.substring(0, tamanhoDesejado);

        } else if (str.length() < tamanhoDesejado) {
            for (int i = str.length(); i < tamanhoDesejado; i++) {
                str += " ";
            }
        }
        return str;
    }

    /**
     * Método responsável por validar o tamanho do valor de uma String.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 27/08/2014
     * @param str
     * @param tamanhoEsperado
     * @return
     */
    public static boolean validaTamanhoString(String str, int tamanhoEsperado) {
        if (str.length() != tamanhoEsperado) {
            return false;
        }
        return true;
    }

    /**
     * Método responsável por validar a presença apenas de letras nos dados
     * informados pelo usuário.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean somenteLetras(String text) {
        String letra;
        for (int i = 0; i < text.length(); i++) {
            letra = text.substring(i, i + 1); // Letra recebe caracter atual do loop

            if (letra.matches("\\d")) {
                // Se a letra estiver dentro da ER retorna FALSE, pois apenas letras são suportadas.
                return false;
            }
        }
        return true;
    }

    /**
     * Método responsável por validar a presen;a apenas de números nos dados
     * informados pelo usuário.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean somenteNumeros(String text) {
        String letra;
        for (int i = 0; i < text.length(); i++) {
            letra = text.substring(i, i + 1); // Letra recebe caracter atual do loop

            if (!letra.matches("\\d")) {
                // Se a letra estiver dentro da ER retorna FALSE, pois apenas letras são suportadas.
                return false;
            }
        }
        return true;
    }

    /**
     * Método responsável por validar hora do tipo Time
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 25/06/2015
     * @param text
     * @return
     */
    public static boolean validaEmail(String text) {
        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(text);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    /**
     * Método responsável por validar e-mail.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean validaHora(String text) {
        text = text.concat(":00");
        try {
            java.sql.Time hora = Time.valueOf(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Método responsável por validar campo, não deixando-o vazio.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean validaCampoVazio(String text) {
        if ((text == null) || (text.equals(""))) {
            return false;
        }
        return true;
    }

    /**
     * Método responsável por validar CNPJ.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean validaCnpj(String text) {
        if (text.equals("00000000000000") || text.equals("11111111111111")
                || text.equals("22222222222222") || text.equals("33333333333333")
                || text.equals("44444444444444") || text.equals("55555555555555")
                || text.equals("66666666666666") || text.equals("77777777777777")
                || text.equals("88888888888888") || text.equals("99999999999999")
                || (text.length() != 14)) {
            return (false);
        }
        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (text.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (text.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            if ((dig13 == text.charAt(12)) && (dig14 == text.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    /**
     * Método responsável por validar dados do tipo double.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean validaDouble(String text) {
        try {
            Double numero = Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método responsável por validar dados do tipo inteiro.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 16/06/2015
     * @param text
     * @return
     */
    public static boolean validaInt(String text) {
        try {
            Integer numero = Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método responsável por validar datas.
     *
     * @author Gabriel Arsênio da Silva
     * @since v1.00 05/08/2014
     * @param text
     * @return
     */
    public static boolean validaData(String text) {
        if (text.trim().length() < 10) {
            return false;
        }

        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(text);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
