# Hasher, Web, 100pts

## Problem

There's this system that has a hardcoded admin user/password, in a way that can not be brute forced or cracked. We desperately need to acquire access to this system, can you help us?

## Solution

We get simple form, where we can put our string.

Also, we are able to see PHP source of the challenge:

```php
 <?php
if (!shell_exec("which openssl"))
    die("Challenge Error: need openssl installed\n");
if (isset($_GET['code']))
    die(highlight_file(__FILE__));
function str_xor($str,$max_depth=0,$depth=0)
{
    $mid=strlen($str)/2;
    $left=substr($str,0,$mid);
    $right=substr($str,$mid);
    if ($depth<$max_depth)
    {
        $left=str_xor($left,$max_depth,$depth+1);
        $right=str_xor($right,$max_depth,$depth+1);
    }
    $out="";
    for ($i=0;$i<strlen($left);++$i)
        $out.=$left[$i]^$right[$i];
    return $out;
}
function hasher($string)
{
    if (!ctype_alnum($string))
        return null;
    $t=trim(shell_exec("echo -n '{$string}' | openssl dgst -whirlpool | openssl dgst -rmd160"));
    $t=str_replace("(stdin)= ","",$t); //some linux adds this
    if (!$t)
        return null;
    return bin2hex(str_xor(hex2bin($t),1));
}
$user='admin';
extract($_POST);
if (isset($password))
{
    if (hasher($user)==hasher($password) and $user!=$password)
        echo "Welcome! Flag is: ".include("flag.php");
    else
        echo "Invalid password.<br/>";
}
?>
<form method='post'>
    <label>Password:</label>
    <input type='password' name='password' />
    <input type='submit' />
</form>
<a href='./?code'>Source Code</a> 1
```

So we need `if (hasher($user)==hasher($password) and $user!=$password)` i.e different `$user` and `$password` but an equal `hasher()`. In fact we don't need `hasher()` to return the same result thanks to == similar to the problem `MD5 Games 1`.  

In fact we want `hasher($user)` and `hasher($password)` to match `/^0e[0-9]{8}$/`.  

We re-use the source code to write a php script:  


```php
<?php
function str_xor($str,$max_depth=0,$depth=0)
{
    $mid=strlen($str)/2;
    $left=substr($str,0,$mid);
    $right=substr($str,$mid);
    if ($depth<$max_depth)
    {
        $left=str_xor($left,$max_depth,$depth+1);
        $right=str_xor($right,$max_depth,$depth+1);
    }
    $out="";
    for ($i=0;$i<strlen($left);++$i)
        $out.=$left[$i]^$right[$i];
    return $out;
}
function hasher($string)
{
    if (!ctype_alnum($string))
        return null;
    $t=trim(shell_exec("echo -n '{$string}' | openssl dgst -whirlpool | openssl dgst -rmd160"));
    $t=str_replace("(stdin)= ","",$t); //some linux adds this
    if (!$t)
        return null;
    return bin2hex(str_xor(hex2bin($t),1));
}
$found = 0;
$user = "";
$password = "";
for ($i=0; ; ++$i) {
    if ( hasher("$i") == "0e$i") {
        echo "i: $i // hasher(i): " . hasher("$i") . "\n";
        $found += 1;
        if ($found == 1) $user=$i;
        elseif ($found == 2) $password=$i;
    } elseif ($found == 2) {
        break;
    }
}
if (hasher($user)==hasher($password) and $user!=$password)
    echo "user: $user / password: $password \n";
```

Result of above script will be:

```
i: 29588 // hasher(i): 0e34131778
i: 56392 // hasher(i): 0e64927533
user: 29588 / password: 56392
```
We send the username and password as POST data to the site using BurpSuite/Postman, and we get the result:  
 `Welcome! Flag is: g1diXbB2kfaGjS0V`
