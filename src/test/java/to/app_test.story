Scenario: Klient zakłada nowe konto (nieletni)
Given bank
And klient ma 16 lat
When klient składa wniosek o założenie konta
Then konto nie powinno zostać stworzone

Scenario: Klient zakłada nowe konto (pełnoletni)
Given bank
And klient ma 20 lat
When klient składa wniosek o założenie konta
Then konto powinno zostać stowrzone

Scenario: Klient loguje się w bankomacie (poprawny PIN)
Given bankomat
And karta w bankomacie
When klient wprowadzi poprawny PIN
Then klient powinien być zalogowany w bankomacie

Scenario: Klient loguje się w bankomacie (niepoprawny PIN)
Given bankomat
And karta w bankomacie
When klient wprowadzi niepoprawny PIN
Then klient nie powinien być zalogowany w bankomacie
And karta nie powinna być w bankomacie

Scenario: Klient wypłaca gotówkę z bankomatu
Given bankomat
And klient jest zalogowany w bankomacie
And saldo bankomatu wynosi 500 złotych
And saldo konta klienta wynosi 100 złotych
When klient wypłaci 50 złotych
Then saldo konta klienta powinno wynosić 50 złotych
And saldo bankomatu powinno wynosić 450 złotych

Scenario: Klient wpłaca gotówkę do bankomatu
Given bankomat
And klient jest zalogowany w bankomacie
And saldo bankomatu wynosi 500 złotych
And saldo konta klienta wynosi 100 złotych
When klient wpłaci 50 złotych
Then saldo konta klienta powinno wynosić 150 złotych
And saldo bankomatu powinno wynosić 550 złotych
