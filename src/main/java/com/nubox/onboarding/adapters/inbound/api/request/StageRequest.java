package com.nubox.onboarding.adapters.inbound.api.request;

import com.nubox.onboarding.domain.model.Stage;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class StageRequest {

    @NotNull
    private Integer id;

    @Valid
    @NotEmpty
    private List<ElementRequest> elements;

    public Stage toDomain() {
        Stage stage = new Stage();
        stage.setId(id);
        stage.setElements(elements.stream().map(ElementRequest::toDomain).collect(Collectors.toList()));
        return stage;
    }

}
