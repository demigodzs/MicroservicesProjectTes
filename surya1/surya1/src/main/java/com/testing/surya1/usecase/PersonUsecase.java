package com.testing.surya1.usecase;

import com.testing.surya1.models.Person;
import com.testing.surya1.models.response.ResponseInfo;
import com.testing.surya1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PersonUsecase {
    @Autowired
    public PersonRepository personRepository;

    public ResponseInfo<Object> addPerson(String requestId, Person person)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();
        try
        {
            person.setCreatedBy("user_1");
            person.setCreatedDate(Date.from(Instant.now()));

            Person personData = this.personRepository.save(person);

            if(personData.equals(person))
            {
                responseInfo.setSuccess();
            }
            else
            {
                responseInfo.setBadRequestException("Save failed, kindly check your request");
            }
        }
        catch (Exception e)
        {
            responseInfo.setException(e);
        }

        return responseInfo;
    }

    public ResponseInfo<Object> getPersons(String requestId)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();
        try
        {
            List<Person> persons = this.personRepository.findByIsActive(true);

            responseInfo.setSuccess(persons);
        }
        catch (Exception e)
        {
            responseInfo.setException(e);
        }

        return responseInfo;
    }

    public ResponseInfo<Object> getPersonById(String requestId, Long id)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();
        try
        {
            Optional<Person> person = this.personRepository.findById(id);

            if(person.isPresent())
            {
                responseInfo.setSuccess(person);
            }
            else
            {
                responseInfo.setNotFoundException("Not found, please check your request.");
            }
        }
        catch (Exception e)
        {
            responseInfo.setException(e);
        }

        return responseInfo;
    }

    public ResponseInfo<Object> editPerson(String requestId, Long id, Person person)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();
        try
        {
            Optional<Person> personData = this.personRepository.findById(id);

            if(personData.isPresent())
            {
                person.setId(id);
                person.setModifyBy("user_2");
                person.setModifyDate(Date.from(Instant.now()));
                person.setCreatedBy(personData.get().getCreatedBy());
                person.setCreatedDate(personData.get().getCreatedDate());
                this.personRepository.save(person);

                responseInfo.setSuccess();
            }
            else
            {
                responseInfo.setNotFoundException("Not found, please check your request.");
            }
        }
        catch (Exception e)
        {
            responseInfo.setException(e);
        }

        return responseInfo;
    }

    public ResponseInfo<Object> deletePerson(String requestId, Long id)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();
        try
        {
            Optional<Person> personData = this.personRepository.findById(id);

            if(personData.isPresent())
            {
                Person person = new Person();
                person.setId(id);
                person.setIsActive(false);
                person.setModifyBy("user_3");
                person.setModifyDate(Date.from(Instant.now()));
                person.setCreatedBy(personData.get().getCreatedBy());
                person.setCreatedDate(personData.get().getCreatedDate());
                person.setName(personData.get().getName());
                person.setEmail(personData.get().getEmail());
                this.personRepository.save(person);

                responseInfo.setSuccess();
            }
            else
            {
                responseInfo.setNotFoundException("Not found, please check your request.");
            }
        }
        catch (Exception e)
        {
            responseInfo.setException(e);
        }

        return responseInfo;
    }

    public ResponseInfo<Object> searchPersonByKeyword(String requestId, String keyword)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();
        try
        {
            if(keyword.equals(""))
            {
                List<Person> persons = this.personRepository.findByIsActive(true);
                responseInfo.setSuccess(persons);
            }
            else
            {
                List<Person> persons = this.personRepository.searchByKeywordName(keyword);
                responseInfo.setSuccess(persons);
            }
        }
        catch (Exception e)
        {
            responseInfo.setException(e);
        }

        return responseInfo;
    }
}
