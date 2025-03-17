package org.fastcampus.auth.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.fastcampus.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    @PostMapping("/send-verification-email")
    public Response<Void> sendMail(@RequestBody SendEmailRequestDto dto) {
        return Response.ok(null);
    }

}
