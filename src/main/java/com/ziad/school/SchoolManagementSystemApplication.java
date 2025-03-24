package com.ziad.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
//@EnableCaching
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)//For a Stable JSON Structure when requesting Paginated data
public class SchoolManagementSystemApplication {
//    String[] fNames = {"Ziad", "Ahmed", "Mohamed", "Andrew", "Scott", "Marwan", "Mazen", "Ramy", "Malek", "Rana", "Esraa", "Samira", "Mawda", "Samya", "Loka", "Knda", "Ali", "Aser", "Mostafa", "Hamada", "Amanda", "Wilson", "Sam", "Remy", "Lala"};
//    String[] lNames = {"Ziad", "Ahmed", "Mohamed", "Andrew", "Scott", "Marwan", "Mazen", "Ramy", "Malek", "Loka", "Ali", "Aser", "Mostafa", "Hamada"};

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementSystemApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
//        return args -> {
//            Random rand = new Random(0);
//            for (int i = 0; i < fNames.length; i++) {
//                for (int j = 0; j < lNames.length; j++) {
//                    String email = fNames[i] + lNames[j] + i + j + "@gmail.com";
//                    Student student = new Student();
//                    student.setFirstName(fNames[i]);
//                    student.setLastName(lNames[j]);
//                    student.setEmail(email);
//                    student.setIsActive(true);
//                    student.setPassword("P@ssw0rd");
//                    try {
//                        System.out.println(studentRepository.save(student).toString());
//                    } catch (DataIntegrityViolationException e) {
//                        continue;
//                    }
//                }
//
//
//            }
//        };
//    }
}
