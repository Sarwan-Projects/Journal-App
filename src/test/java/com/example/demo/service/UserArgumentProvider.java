package com.example.demo.service;

import com.example.demo.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider
{

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception
    {
        return Stream.of(
                Arguments.of(User.builder().username("shyam").password("shyam").build()),
                Arguments.of(User.builder().username("kamal").password("").build())
        );
    }
}
