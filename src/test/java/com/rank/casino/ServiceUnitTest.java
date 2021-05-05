package com.rank.casino;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import com.rank.casino.model.Player;
import com.rank.casino.model.RequestInfo;
import com.rank.casino.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceUnitTest
{
    @Mock
    PlayerRepository playerRepository;
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    RestService service;
    @Test
    public void testGetBalance(){
        Long playerId = 1L;
        Transaction transaction = new Transaction(1L, 1000.0,1);
        Player player = new Player(1000.0, "Sharon");
        player.setId(1L);
        when(playerRepository.findByUsername(any(String.class))).thenReturn(player);
        when(playerRepository.existsById(any(Long.class))).thenReturn(true);
        when(playerRepository.findById(any(Long.class))).thenReturn(Optional.of(player));
        Exception exception = null;
        try {
            Double balance = service.getBalance(playerId);
            assertEquals(balance, player.getBalance());
        }catch (Exception e){
            exception = e;
        }
        assertNull(exception);
    }
}