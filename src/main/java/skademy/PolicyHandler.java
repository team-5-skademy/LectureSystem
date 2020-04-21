package skademy;

import skademy.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PolicyHandler{


    @Autowired
    LectureSystemRepository lectureSystemRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCourseRegistrationCompleted_수강자수변경(@Payload CourseRegistrationCompleted courseRegistrationCompleted){

        if(courseRegistrationCompleted.isMe()){

            System.out.println("courseRegistrationCompleted::" + courseRegistrationCompleted.toJson());
            Long lectureId = courseRegistrationCompleted.getLectureId();
            Optional<LectureSystem> lectureSystemOptional = lectureSystemRepository.findById(lectureId);
            LectureSystem lectureSystem = lectureSystemOptional.get();
            int studentCnt = lectureSystem.getStudentNumber();

            System.out.println("##### 수강자수 :" + studentCnt + " ");

            lectureSystem.setStudentNumber(studentCnt++);
            lectureSystemRepository.save(lectureSystem);

            System.out.println("##### listener 수강자수 + 1 변경 완료");
        }
    }

/*    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_수강자수변경(@Payload Long lectureId){

        Optional<LectureSystem> lectureSystemOptional = lectureSystemRepository.findById(lectureId);
        LectureSystem lectureSystem = lectureSystemOptional.get();

        int studentCnt = lectureSystem.getStudentNumber();
        lectureSystem.setStudentNumber(studentCnt--);
        lectureSystemRepository.save(lectureSystem);

        System.out.println("##### listener 수강자수 - 1 변경 완료");
    }*/



}
