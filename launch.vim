nnoremap <silent><nowait>r :sp<CR><C-w>w<C-w>10_:term javac *.java;java Main<CR>
nnoremap <silent><nowait>q :!rm -rf *.class<CR><CR>:echo "クラスファイル消去"<CR>
nnoremap F :call Search(expand("<cword>"))<CR>
color habamax

function! Search(word)
  let files=[]
  for i in readdir("/Users/kamui-nakahara/programs/java2/")
    if i!=".DS_Store"
      for j in readdir("/Users/kamui-nakahara/programs/java2/".i)
	if match(j,".java")!=-1
	  if match(join(readfile("/Users/kamui-nakahara/programs/java2/".i."/".j),"\n"),a:word)!=-1
	    call add(files,i)
	    break
	  endif
	endif
      endfor
    endif
  endfor
  if files==[]
    echo a:word."は見つかりませんでした"
  else
    vs
    normal ffw
    Ex /Users/kamui-nakahara/programs/java2
  endif
  for i in files
    call matchadd("s",i)
  endfor
  hi! s ctermfg=red
endfunction
