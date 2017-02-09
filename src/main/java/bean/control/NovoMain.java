/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.control;

/**
 *
 * @author Daniel
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String telefone = "(37)3214-2163";
//
//        String tel[] = convertVal(telefone, telefone.length());
//        final int ddd = 1, tA = 2;
//
//        boolean valida = false;
//
//        if (!verificaVal(quebraVal(joinVal(tel)))) {
//            if (validaDDD(tel[ddd])) {
//                if (verPriDigito(tel[tA])) {
//                } else {
//                    valida = true;
//                }
//            } else {
//                valida = true;
//            }
//        } else {
//            valida = true;
//        }
//        
//        if(valida){
//            System.out.println("telefone invalido");
//        }

    }

//    /**
//     * Convert String com mascara em um Array de String
//     *
//     * @param var
//     * @param tipo
//     * @return
//     */
//    private static String[] convertVal(String var, int tipo) {
//        if (tipo == 14 && var.contains(".")) {
//            return var.split("[.-]");
//        } else {
//            return var.split("[()-]");
//        }
//    }

    /**
     * Valida DDD
     *
     * @param ddd
     * @return
     */
//    private static boolean validaDDD(String ddd) {
//        int[] newDDD = quebraVal(ddd);
//
//        for (int i = 0; i < newDDD.length; i++) {
//            if (newDDD[i] == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * Verifica se o primeiro digito é 0 ou 1
     *
     * @return
     */
    private static boolean verPriDigito(String var) {
//        int[] tel = quebraVal(var);
//        int pD = tel[tel.length - 1];
//
//        return !(pD == 0 || pD == 1);
        return true;
    }

//    /**
//     * Verifica de todos os digitos sao iguais
//     */
//    private static boolean verificaVal(int[] val) {
//        int cont = 0;
//
//        for (int i = 0; i < val.length - 1; i++) {
//            if (val[i] == val[i + 1]) {
//                cont++;
//            }
//        }
//        return cont == (val.length - 1);
//    }

//    /**
//     * Transforma int em Array de int
//     *
//     * @param val
//     * @return
//     */
//    private static int[] quebraVal(String val) {
//        Long i = Long.valueOf(val);
//        int[] ary = new int[val.length()];
//
//        for (int j = 0; j < ary.length; j++) {
//            ary[j] = (int) (i % 10);
//
//            i /= 10;
//        }
//        return ary;
//    }

//    /**
//     * Junta valores de um Array em uma String
//     *
//     * @param val
//     * @return
//     */
//    private static String joinVal(String[] val) {
//        String newVal = "";
//
//        for (String string : val) {
//            newVal += string;
//        }
//        return newVal;
//    }

    /**
     * [1-9]{2}\-[2-9][0-9]{7,8}
     *
     * OS DIGITOS NÃO PODEM SER TODOS IGUAIS (33)3333-3333 *
     *
     * DDD | Dois digitos de 1 a 9. Não existem códigos de DDD com o dígito 0 *
     *
     * O PRIMEIRO DIGITO NUNCA SERÁ 0 ou 1. Reservado para emergencias 193...
     *
     * OS DEMAIS 7 ou 8 DIGITOS PODEM IR DE 0 a 9 *
     */
}
