using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary
{
    public class Composition : IComponent
    {
        public string Name { get; set; }

        //private List<Component> components;
        private Composite _composite;

        public Composition(string name, Compositor compositor) 
        {
            _composite = new Composite(name);
            Name = name;
        }

        public void Add(IComponent component)
        {
            _composite.Add(component);
            //_composite = (Composite)component;
        }

        public void Draw(Graphics graphics)
        {
            //components.ForEach(a => a.Draw(graphics));
            _composite.Draw(graphics);
        }

        public void Remove(IComponent component)
        {
            throw new NotImplementedException();
        }

        public IComponent GetChild(int index)
        {
            throw new NotImplementedException();
        }

        public void Compose() // TODO: udskift med compositor
        {
            _composite.SortComponents();

            string tt = "";
            foreach (IComponent component in _composite)
            {
                tt += component.Name;
                System.Diagnostics.Debug.WriteLine(tt);
            }

            for(int ix = 0; ix < _composite.Count(); ix++)
            {
                IComponent component = _composite.GetChild(ix);
                IComponent nextComponent = _composite.GetChild(ix + 1);
                if (component.GetType() != nextComponent.GetType())
                {
                    _composite.Add(new ComponentNewLine("Newline"));
                }

            }
        }
    }
}
