package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryTeacherRepository {
    private List<Teacher> teacherList;

    public InMemoryTeacherRepository() {
//        teacherList=new ArrayList<>();
//        Teacher t1=new Teacher(0l,"Ivan","Corbev");
//        Teacher t2=new Teacher(1l,"Dimitar","Trajanov");
//        Teacher t3=new Teacher(2l,"Riste","Stojanov");
//        Teacher t4=new Teacher(3l,"Kostadin","Mishev");
//        Teacher t5=new Teacher(4l,"Ana","Todorovska");
//        teacherList.add(t1);
//        teacherList.add(t2);
//        teacherList.add(t3);
//        teacherList.add(t4);
//        teacherList.add(t5);
    }
    public List<Teacher> findAll(){
        return teacherList;
    }
}
