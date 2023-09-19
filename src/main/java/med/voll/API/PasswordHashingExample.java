package med.voll.API;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashingExample {
    public static void main(String[] args) {
        // Genera una contraseña aleatoria
        String password = "123456"; // Reemplaza esto con tu contraseña segura

        // Crea un objeto BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Aplica el hashing a la contraseña
        String hashedPassword = passwordEncoder.encode(password);

        // Imprime la contraseña hash
        System.out.println("Contraseña hash: " + hashedPassword);
    }
}
