package posmy.interview.boot.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import posmy.interview.boot.service.exception.AddBookException;
import posmy.interview.boot.service.exception.AddUserException;
import posmy.interview.boot.service.exception.BookException;
import posmy.interview.boot.service.exception.BookNotFoundException;
import posmy.interview.boot.service.exception.UpdateBookException;
import posmy.interview.boot.service.exception.UpdateUserException;
import posmy.interview.boot.service.exception.UserException;
import posmy.interview.boot.service.exception.UserNotFoundException;

/**
 * A customiser Rest controller advice.
 * 
 * @author mokht
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = AddBookException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleUserException(AddBookException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				request.getDescription(false), LocalDateTime.now());
	}

	@ExceptionHandler(value = AddUserException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleUserException(AddUserException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				request.getDescription(false), LocalDateTime.now());
	}

	@ExceptionHandler(value = BookException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleUserException(BookException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

	@ExceptionHandler(value = BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleUserException(BookNotFoundException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

	@ExceptionHandler(value = UpdateBookException.class)
	@ResponseStatus(HttpStatus.NOT_MODIFIED)
	public ErrorMessage handleUserException(UpdateBookException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_MODIFIED.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

	@ExceptionHandler(value = UpdateUserException.class)
	@ResponseStatus(HttpStatus.NOT_MODIFIED)
	public ErrorMessage handleUserException(UpdateUserException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_MODIFIED.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

	@ExceptionHandler(value = UserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleUserException(UserException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleUserException(UserNotFoundException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
	}

}
