public static int[] manchesterEncode(int[] input) {
  // Inicializar o tamanho da saída como o dobro do tamanho da entrada
  int[] output = new int[input.length * 2];

  // Percorrer cada bit da entrada
  for (int i = 0; i < input.length; i++) {
    // Adicionar o primeiro bit da codificação Manchester
    if (input[i] == 0) {
      output[i * 2] = 1;
      output[i * 2 + 1] = 0;
    } else {
      output[i * 2] = 0;
      output[i * 2 + 1] = 1;
    }
  }

  // Retornar a saída
  return output;
}

public static int[] manchesterDifferentialEncode(int[] input) {
  // Inicializar o tamanho da saída como o dobro do tamanho da entrada
  int[] output = new int[input.length * 2];

  // Inicializar o bit anterior como '0'
  int lastBit = 0;

  // Percorrer cada bit da entrada
  for (int i = 0; i < input.length; i++) {
    // Adicionar o primeiro bit da codificação Manchester diferencial
    if (input[i] == lastBit) {
      output[i * 2] = 0;
    } else {
      output[i * 2] = 1;
      lastBit = input[i];
    }

    // Adicionar o segundo bit da codificação Manchester diferencial
    if (input[i] == lastBit) {
      output[i * 2 + 1] = 1;
    } else {
      output[i * 2 + 1] = 0;
    }
  }

  // Retornar a saída
  return output;
}
