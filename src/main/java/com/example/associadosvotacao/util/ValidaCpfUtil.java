package com.example.associadosvotacao.util;

public class ValidaCpfUtil {
    public static boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11)
            return false;

        // Verifica se todos os dígitos são iguais
        boolean iguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                iguais = false;
                break;
            }
        }
        if (iguais)
            return false;

        // Calcula os dígitos verificadores
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }
        int soma1 = digitos[0] * 10 + digitos[1] * 9 + digitos[2] * 8 + digitos[3] * 7 + digitos[4] * 6 + digitos[5] * 5 + digitos[6] * 4 + digitos[7] * 3 + digitos[8] * 2;
        int resto1 = soma1 % 11;
        int dv1 = resto1 < 2 ? 0 : 11 - resto1;
        int soma2 = digitos[0] * 11 + digitos[1] * 10 + digitos[2] * 9 + digitos[3] * 8 + digitos[4] * 7 + digitos[5] * 6 + digitos[6] * 5 + digitos[7] * 4 + digitos[8] * 3 + dv1 * 2;
        int resto2 = soma2 % 11;
        int dv2 = resto2 < 2 ? 0 : 11 - resto2;

        return digitos[9] == dv1 && digitos[10] == dv2;
    }
}
