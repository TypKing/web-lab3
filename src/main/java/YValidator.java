import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("YValidator")
public class YValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(!continueValidation()) {
            return;
        }
        else {
            try {
                System.out.println("Валидирую 1");
                if (value.toString().length() == 0 || value.toString().trim().equals("")) {
                    System.out.println("Валидирую 2");
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Введите значение в поле Y");
                    throw new ValidatorException(msg);
                }
                System.out.println("Валидирую 3");
                double y = Double.parseDouble(value.toString().trim().replace(",", "."));
                if (y > 3 || y < -5) {
                    System.out.println("Валидирую 4");
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Значение Y должно быть [-5;3]");
                    throw new ValidatorException(msg);
                }
            } catch (IllegalArgumentException e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Неверный формат данных");
                throw new ValidatorException(msg);
            }
        }
    }
    protected boolean continueValidation() {
        String skipValidator= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
        if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }
}

