package service.demo.store.customerservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8810589628486864059L;

    private final int serviceCode;
    private final String msg;
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String[] elements;

    public ServiceException(int serviceCode) {
        this.serviceCode    = serviceCode;
        this.msg            =  " "+serviceCode;
        this.elements       = null;
    }

    public ServiceException(int serviceCode, String ... elements) {
        this.serviceCode    = serviceCode;
        this.msg            =  " "+serviceCode;
        this.elements       = elements;
    }

    public ServiceException(int serviceCode, HttpStatus status, String ... elements) {
        this.serviceCode    = serviceCode;
        this.msg            =  " "+serviceCode;
        this.status         = status;
        this.elements       = elements;
    }


    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public String getLocalizedMessage() {
        return this.msg;
    }
}
