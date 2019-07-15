import { Injectable, Renderer2, RendererFactory2, ElementRef} from '@angular/core';
import { Message } from '../entities/Message';


@Injectable({
  providedIn: 'root'
})
export class NewMessageService {
  private renderer:Renderer2;
  constructor(rendererFactory: RendererFactory2) {
    this.renderer= rendererFactory.createRenderer(null, null);
  }
    
    addNewMessage(message: Message, className: string):void {
      let child:HTMLDivElement = this.renderer.createElement('li');
      let text = this.renderer.createText(message.author+ ": "+ message.message);

      this.renderer.appendChild(child, text);
      this.renderer.addClass(child, className);
      let elems = document.getElementsByClassName('chat-window-msgs')
      let ul;
      for(let i = 0; i< elems.length; i++) {
         if(!elems[i]['hidden']) {
            ul = elems[i];
         }
      }
      this.renderer.appendChild(ul, child);
    }
}
